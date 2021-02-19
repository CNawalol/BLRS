/**
 * @author awalol
 * @date 2021/2/16
 */
fun main() {
    val rid = "R1Ax411w7iA"
//    println("hello/kakak/la.flv".replace(".flv",""))
//    System.getProperties().forEach { entry -> println(entry) }
    System.setProperty("ffmpeg.path","D:\\Program Files (x86)\\FormatFactory\\ffmpeg.exe")
    println(RecordUtil.getRoomInfo(rid).data!!.liveRecordInfo!!.title)
    val video = RecordUtil.downloadRecord(rid)
    RecordUtil.dmDownload(rid)
    RecordUtil.ffmpegVideoMerge(video, System.getProperty("user.dir").plus("/$rid/$rid.flv"))
}