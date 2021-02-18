package beans

import com.fasterxml.jackson.annotation.JsonProperty

data class DMMsgBean(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: Data? = null,

	@field:JsonProperty("message")
	val message: String? = null,

	@field:JsonProperty("ttl")
	val ttl: Int? = null
)

data class Dm(

	@field:JsonProperty("interactive_info")
	val interactiveInfo: Any? = null,

	@field:JsonProperty("dm_info")
	val dmInfo: ArrayList<DmInfoItem?>? = null
)

data class CheckInfo(

	@field:JsonProperty("check_token")
	val checkToken: String? = null,

	@field:JsonProperty("ts")
	val ts: Long? = null
)

data class DmInfoItem(

	@field:JsonProperty("dm_mode")
	val dmMode: Int? = null,

	@field:JsonProperty("dm_id")
	val dmId: String? = null,

	@field:JsonProperty("title")
	val title: List<String?>? = null,

	@field:JsonProperty("dm_color")
	val dmColor: Int? = null,

	@field:JsonProperty("is_admin")
	val isAdmin: Int? = null,

	@field:JsonProperty("dm_type")
	val dmType: Int? = null,

	@field:JsonProperty("uid")
	val uid: Int? = null,

	@field:JsonProperty("check_info")
	val checkInfo: CheckInfo? = null,

	@field:JsonProperty("mobile_verify")
	val mobileVerify: Int? = null,

	@field:JsonProperty("bubble")
	val bubble: Int? = null,

	@field:JsonProperty("user_hash")
	val userHash: String? = null,

	@field:JsonProperty("nickname")
	val nickname: String? = null,

	@field:JsonProperty("medal")
	val medal: List<String?>? = null,

	@field:JsonProperty("user_level")
	val userLevel: List<String?>? = null,

	@field:JsonProperty("rank")
	val rank: Int? = null,

	@field:JsonProperty("msg_type")
	val msgType: Int? = null,

	@field:JsonProperty("text")
	val text: String? = null,

	@field:JsonProperty("vip")
	val vip: Int? = null,

	@field:JsonProperty("svip")
	val svip: Int? = null,

	@field:JsonProperty("uname_color")
	val unameColor: String? = null,

	@field:JsonProperty("ts")
	val ts: Int? = null,

	@field:JsonProperty("guard_level")
	val guardLevel: Int? = null,

	@field:JsonProperty("dm_fontsize")
	val dmFontsize: Int? = null
)

data class Data(

	@field:JsonProperty("dm")
	val dm: Dm? = null,

	@field:JsonProperty("md5")
	val md5: String? = null
)
