import java.util.ArrayList;

/**
 * @author awalol
 * @date 2021/2/20
 */
public class Main {
    public static void main(String[] args) {
        String rid = "R1Ax411w7iA";
        System.setProperty("ffmpeg.path","D:\\Program Files (x86)\\FormatFactory\\ffmpeg.exe");
        System.out.println(RecordUtil.getRoomInfo(rid).getData().getLiveRecordInfo().getTitle());
        ArrayList<String> video = RecordUtil.downloadRecord(rid);
        RecordUtil.dmDownload(rid);
        RecordUtil.ffmpegVideoMerge(video, System.getProperty("user.dir") + String.format("/%s/%s.flv",rid,rid));
    }
}
