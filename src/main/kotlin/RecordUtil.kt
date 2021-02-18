import beans.DMMsgBean
import beans.RoomInfoBean
import beans.UrlBean
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.*
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import java.io.BufferedReader







/**
 * @author awalol
 * @date 2021/2/16
 */
object RecordUtil {
    private val cm = PoolingHttpClientConnectionManager()
    private val httpClient: HttpClient = HttpClients.custom().setConnectionManager(cm).build()

    init {
        cm.maxTotal = 200
        cm.defaultMaxPerRoute = 20
    }

    fun getRoomInfo(rid: String): RoomInfoBean {
        val objectMapper = ObjectMapper()
        val httpResponse: HttpResponse = httpClient.execute(HttpGet("https://api.live.bilibili.com/xlive/web-room/v1/record/getInfoByLiveRecord?rid=".plus(rid)))
        val httpContent = EntityUtils.toString(httpResponse.entity, "UTF-8")
        return objectMapper.readValue(httpContent, RoomInfoBean::class.java);
    }

    fun downloadRecord(rid: String) : ArrayList<String>{
        val videoPaths = ArrayList<String>()
        val objectMapper = ObjectMapper()
        val dir = File(System.getProperty("user.dir").plus("/$rid"))
        dir.mkdirs()
        val httpResponse: HttpResponse = httpClient.execute(HttpGet("https://api.live.bilibili.com/xlive/web-room/v1/record/getLiveRecordUrl?rid=$rid&platform=flash"))
        val httpContent = EntityUtils.toString(httpResponse.entity, "UTF-8")
        val urlBean = objectMapper.readValue(httpContent, UrlBean::class.java)
        var i = 0
        val threadList : ArrayList<FileDownloadThread> = ArrayList()
        for(i in i..urlBean.data!!.list!!.lastIndex){
//            videoPaths.add(fileDownload(urlBean.data.list!![i]!!.url!!,File(dir.absolutePath.plus("/$i.flv"))))
            val downloadThread = FileDownloadThread(urlBean.data.list!![i]!!.url!!,File(dir.absolutePath.plus("/$i.flv")))
            downloadThread.start()
            threadList.add(downloadThread)
        }
        //wait Thread to stop
        for(thread in threadList){
            thread.join()
            videoPaths.add(thread.videoPath)
        }
        return videoPaths
    }

    fun fileDownload(url: String, file: File) : String{
        println(file.absolutePath + " | " + url)
        val httpResponse: HttpResponse = httpClient.execute(HttpGet(url))
        //写入文件
        val dataOutputStream = DataOutputStream(FileOutputStream(file))
        //https://www.cnblogs.com/Scott007/p/3817285.html
        var l : Int;
        val tmp = ByteArray(DEFAULT_BUFFER_SIZE)
        while (httpResponse.entity.content.read(tmp).also { l = it } !== -1) {
            val bytes = ByteArray(l)
            System.arraycopy(tmp, 0, bytes, 0, l)
            dataOutputStream.write(bytes)
        }
        dataOutputStream.flush()
        dataOutputStream.close()
        return file.absolutePath //返回视频路径
    }

    fun dmDownload(rid : String){
        val roomInfo = getRoomInfo(rid)
        val totalIndex = roomInfo.data!!.dmInfo!!.num!!.plus(-1)
        val objectMapper = ObjectMapper()
        var i = 0;
        var dmMsgBean : DMMsgBean? = null;
        for(i in i .. totalIndex){
            val httpResponse : HttpResponse = httpClient.execute(HttpGet("https://api.live.bilibili.com/xlive/web-room/v1/dM/getDMMsgByPlayBackID?rid=$rid&index=$i"))
            val httpContent = EntityUtils.toString(httpResponse.entity)
            if(i == 0){
                dmMsgBean = objectMapper.readValue(httpContent,DMMsgBean::class.java)
            }else{
                val dmMsgBean1 = objectMapper.readValue(httpContent,DMMsgBean::class.java)
                dmMsgBean1.data!!.dm!!.dmInfo!!.forEach { dmInfoItem2 ->
                    dmMsgBean!!.data!!.dm!!.dmInfo!!.add(dmInfoItem2)
                }
            }
        }


        //write jsonfile
        val file = File(System.getProperty("user.dir").plus("/$rid"))
        file.mkdirs()
        val fileWriter = FileWriter(file.absolutePath.plus("/danmu.json"))
        fileWriter.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dmMsgBean!!))
        fileWriter.flush()
        fileWriter.close()
    }

    fun ffmpegVideoMerge(videoPaths : List<String>,outputFilePath : String) {
        val ffmpegPath = System.getProperty("ffmpeg.path")
        val tempDir = System.getProperty("java.io.tmpdir")
        val file = File(tempDir.plus("/temp.txt"))
        val fileWriter = FileWriter(file)
        videoPaths.forEach { action ->
            run {
                println("Start convert $action")
                val outputPath = "${action.substring(0,action.length - 4)}.mp4"
                println(outputPath)
                val convertProcess = Runtime.getRuntime().exec("$ffmpegPath -y -i $action -c copy $outputPath")
                /*val br = BufferedReader(InputStreamReader(convertProcess.errorStream))
                var string : String?
                //https://www.javaroad.cn/articles/3149
                while (br.readLine().also { s -> string = s } != null) {
                    println(string)
                }*/
                //Read stream Thread
                Thread() {
                    //https://www.javaroad.cn/articles/3149
                    val br = BufferedReader(InputStreamReader(convertProcess.errorStream))
                    var string : String?
                    while (br.readLine().also { s -> string = s } != null) {
                        println(string)
                    }
                }.start()

                convertProcess.waitFor()
                fileWriter.append("file '$outputPath'\n")
            }
        }
        fileWriter.flush()
        fileWriter.close()

        //Video merge
        val mergeCommand = "$ffmpegPath -y -f concat -safe 0 -i ".plus(file.absolutePath).plus(" -c copy $outputFilePath")
        println(mergeCommand)
        val mergeProcess = Runtime.getRuntime().exec(mergeCommand)
        Thread() {
            val br = BufferedReader(InputStreamReader(mergeProcess.errorStream))
            var string : String?
            while (br.readLine().also { s -> string = s } != null) {
                println(string)
            }
        }.start()
        mergeProcess.waitFor()
    }

    class FileDownloadThread(private var url : String,private var outfile : File) : Thread() {
        var videoPath = ""
        override fun run() {
            videoPath = fileDownload(url,outfile)
        }
    }
}
