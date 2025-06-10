<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="学校新闻"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="校园论坛"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_name"
				title="学生学生姓名"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_gender"
				title="学生学生性别"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/flea_market/list', 'get')"
				:list="result_flea_market_item_name"
				title="跳蚤市场物品名称"
				source_table="flea_market"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/flea_market/list', 'get')"
				:list="result_flea_market_item_type"
				title="跳蚤市场物品类型"
				source_table="flea_market"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/with_running_service/list', 'get')"
				:list="result_with_running_service_take_goods_on_behalf_of_others"
				title="带跑服务代拿物品"
				source_table="with_running_service"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/campus_perimeter/list', 'get')"
				:list="result_campus_perimeter_store_name"
				title="校园周边店铺名称"
				source_table="campus_perimeter"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/campus_perimeter/list', 'get')"
				:list="result_campus_perimeter_nature_of_business"
				title="校园周边经营范围"
				source_table="campus_perimeter"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_student_student_name":[],
			"result_student_student_gender":[],
			"result_flea_market_item_name":[],
			"result_flea_market_item_type":[],
			"result_with_running_service_take_goods_on_behalf_of_others":[],
			"result_campus_perimeter_store_name":[],
			"result_campus_perimeter_nature_of_business":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取校园论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取student_name
	 */
	get_student_student_name(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_name = json.result.list;
			result_student_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_student_name = result_student_student_name
		 	}
		});
	},
	/**
	 * 获取student_gender
	 */
	get_student_student_gender(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_gender = json.result.list;
			result_student_student_gender.map(o => o.title = o['student_gender'])
	  			this.result_student_student_gender = result_student_student_gender
		 	}
		});
	},
	/**
	 * 获取item_name
	 */
	get_flea_market_item_name(){
		this.$get("~/api/flea_market/get_list?like=0", { page: 1, size: 10, "item_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_flea_market_item_name = json.result.list;
			result_flea_market_item_name.map(o => o.title = o['item_name'])
	  			this.result_flea_market_item_name = result_flea_market_item_name
		 	}
		});
	},
	/**
	 * 获取item_type
	 */
	get_flea_market_item_type(){
		this.$get("~/api/flea_market/get_list?like=0", { page: 1, size: 10, "item_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_flea_market_item_type = json.result.list;
			result_flea_market_item_type.map(o => o.title = o['item_type'])
	  			this.result_flea_market_item_type = result_flea_market_item_type
		 	}
		});
	},
	/**
	 * 获取take_goods_on_behalf_of_others
	 */
	get_with_running_service_take_goods_on_behalf_of_others(){
		this.$get("~/api/with_running_service/get_list?like=0", { page: 1, size: 10, "take_goods_on_behalf_of_others": this.query.word }, (json) => {
		  if (json.result) {
			var result_with_running_service_take_goods_on_behalf_of_others = json.result.list;
			result_with_running_service_take_goods_on_behalf_of_others.map(o => o.title = o['take_goods_on_behalf_of_others'])
	  			this.result_with_running_service_take_goods_on_behalf_of_others = result_with_running_service_take_goods_on_behalf_of_others
		 	}
		});
	},
	/**
	 * 获取store_name
	 */
	get_campus_perimeter_store_name(){
		this.$get("~/api/campus_perimeter/get_list?like=0", { page: 1, size: 10, "store_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_campus_perimeter_store_name = json.result.list;
			result_campus_perimeter_store_name.map(o => o.title = o['store_name'])
	  			this.result_campus_perimeter_store_name = result_campus_perimeter_store_name
		 	}
		});
	},
	/**
	 * 获取nature_of_business
	 */
	get_campus_perimeter_nature_of_business(){
		this.$get("~/api/campus_perimeter/get_list?like=0", { page: 1, size: 10, "nature_of_business": this.query.word }, (json) => {
		  if (json.result) {
			var result_campus_perimeter_nature_of_business = json.result.list;
			result_campus_perimeter_nature_of_business.map(o => o.title = o['nature_of_business'])
	  			this.result_campus_perimeter_nature_of_business = result_campus_perimeter_nature_of_business
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_student_student_name();
		this.get_student_student_gender();
		this.get_flea_market_item_name();
		this.get_flea_market_item_type();
		this.get_with_running_service_take_goods_on_behalf_of_others();
		this.get_campus_perimeter_store_name();
		this.get_campus_perimeter_nature_of_business();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_student_student_name();
	  this.get_student_student_gender();
	  this.get_flea_market_item_name();
	  this.get_flea_market_item_type();
	  this.get_with_running_service_take_goods_on_behalf_of_others();
	  this.get_campus_perimeter_store_name();
	  this.get_campus_perimeter_nature_of_business();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
