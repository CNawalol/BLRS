package beans

import com.fasterxml.jackson.annotation.JsonProperty

data class UrlBean(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: UrlBeanData? = null,

	@field:JsonProperty("message")
	val message: String? = null,

	@field:JsonProperty("ttl")
	val ttl: Int? = null
)

data class QnDescItem(

	@field:JsonProperty("qn")
	val qn: Int? = null,

	@field:JsonProperty("desc")
	val desc: String? = null
)

data class ListItem(

	@field:JsonProperty("size")
	val size: Int? = null,

	@field:JsonProperty("preview_info")
	val previewInfo: PreviewInfo? = null,

	@field:JsonProperty("length")
	val length: Int? = null,

	@field:JsonProperty("backup_url")
	val backupUrl: Any? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

data class PreviewInfo(

	@field:JsonProperty("image")
	val image: List<String?>? = null,

	@field:JsonProperty("img_x_len")
	val imgXLen: Int? = null,

	@field:JsonProperty("img_x_size")
	val imgXSize: Int? = null,

	@field:JsonProperty("img_y_size")
	val imgYSize: Int? = null,

	@field:JsonProperty("img_y_len")
	val imgYLen: Int? = null,

	@field:JsonProperty("pvdata")
	val pvdata: String? = null
)

data class UrlBeanData(

	@field:JsonProperty("size")
	val size: Long? = null,

	@field:JsonProperty("current_qn")
	val currentQn: Int? = null,

	@field:JsonProperty("length")
	val length: Int? = null,

	@field:JsonProperty("list")
	val list: List<ListItem?>? = null,

	@field:JsonProperty("qn_desc")
	val qnDesc: List<QnDescItem?>? = null
)
