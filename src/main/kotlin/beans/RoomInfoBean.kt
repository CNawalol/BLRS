package beans

import com.fasterxml.jackson.annotation.JsonProperty

data class RoomInfoBean(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: RoomInfoData? = null,

	@field:JsonProperty("message")
	val message: String? = null,

	@field:JsonProperty("ttl")
	val ttl: Int? = null
)

data class LiveRecordInfo(

	@field:JsonProperty("room_id")
	val roomId: Int? = null,

	@field:JsonProperty("area_name")
	val areaName: String? = null,

	@field:JsonProperty("start_timestamp")
	val startTimestamp: Int? = null,

	@field:JsonProperty("rid")
	val rid: String? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("area_id")
	val areaId: Int? = null,

	@field:JsonProperty("uid")
	val uid: Int? = null,

	@field:JsonProperty("end_timestamp")
	val endTimestamp: Int? = null,

	@field:JsonProperty("live_screen_type")
	val liveScreenType: Int? = null,

	@field:JsonProperty("parent_area_name")
	val parentAreaName: String? = null,

	@field:JsonProperty("danmu_num")
	val danmuNum: Int? = null,

	@field:JsonProperty("online")
	val online: Int? = null,

	@field:JsonProperty("parent_area_id")
	val parentAreaId: Int? = null
)

data class RecordSwitchInfo(

	@field:JsonProperty("record_tab")
	val recordTab: Boolean? = null
)

data class IndexInfoItem(

	@field:JsonProperty("start_time")
	val startTime: Int? = null,

	@field:JsonProperty("total_num")
	val totalNum: Int? = null,

	@field:JsonProperty("get_time")
	val getTime: Int? = null,

	@field:JsonProperty("end_time")
	val endTime: Int? = null,

	@field:JsonProperty("index")
	val index: Int? = null,

	@field:JsonProperty("interval")
	val interval: Int? = null,

	@field:JsonProperty("md5")
	val md5: String? = null
)

data class RoomInfoData(

	@field:JsonProperty("record_switch_info")
	val recordSwitchInfo: RecordSwitchInfo? = null,

	@field:JsonProperty("host")
	val host: String? = null,

	@field:JsonProperty("live_record_info")
	val liveRecordInfo: LiveRecordInfo? = null,

	@field:JsonProperty("dm_info")
	val dmInfo: DmInfo? = null
)

data class DmInfo(

	@field:JsonProperty("num")
	val num: Int? = null,

	@field:JsonProperty("total_num")
	val totalNum: Int? = null,

	@field:JsonProperty("index_info")
	val indexInfo: List<IndexInfoItem?>? = null
)
