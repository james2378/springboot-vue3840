<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品名称" prop="item_name">
					<el-input id="item_name" v-model="form['item_name']" placeholder="请输入物品名称"
							  v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','item_name')) || (!form['flea_market_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_name')">{{form['item_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品类型" prop="item_type">
					<el-input id="item_type" v-model="form['item_type']" placeholder="请输入物品类型"
							  v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','item_type')) || (!form['flea_market_id'] && $check_field('add','item_type'))" :disabled="disabledObj['item_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_picture') || $check_field('add','item_picture') || $check_field('set','item_picture')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品图片" prop="item_picture">
					<el-upload :disabled="disabledObj['item_picture_isDisabled']" id="item_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_item_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','item_picture')) || (!form['flea_market_id'] && $check_field('add','item_picture'))">
						<img v-if="form['item_picture']" :src="$fullUrl(form['item_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','item_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['item_picture'])" :preview-src-list="[$fullUrl(form['item_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','article_fineness') || $check_field('add','article_fineness') || $check_field('set','article_fineness')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品成色" prop="article_fineness">
					<el-input id="article_fineness" v-model="form['article_fineness']" placeholder="请输入物品成色"
							  v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','article_fineness')) || (!form['flea_market_id'] && $check_field('add','article_fineness'))" :disabled="disabledObj['article_fineness_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','article_fineness')">{{form['article_fineness']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_price') || $check_field('add','item_price') || $check_field('set','item_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品价格" prop="item_price">
					<el-input-number id="item_price" v-model.number="form['item_price']"
						v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','item_price')) || (!form['flea_market_id'] && $check_field('add','item_price'))"></el-input-number>
					<div v-else-if="$check_field('get','item_price')">{{form['item_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','owner') || $check_field('add','owner') || $check_field('set','owner')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物主" prop="owner">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_owner(form['owner']) }}
							<!--<el-input id="business_name" v-model="form['owner']" placeholder="请输入物主"-->
							<!--v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','owner')) || (!form['flea_market_id'] && $check_field('add','owner'))" :disabled="disabledObj['owner_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','owner')">{{form['owner']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','owner')) || (!form['flea_market_id'] && $check_field('add','owner'))" id="owner" v-model="form['owner']" :disabled="disabledObj['owner_isDisabled']">
								<el-option v-for="o in list_user_owner" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','owner')" id="owner" v-model="form['owner']" :disabled="true">
								<el-option v-for="o in list_user_owner" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="owner" v-model="form['owner']" :disabled="disabledObj['owner_isDisabled']">
							<el-option v-for="o in list_user_owner" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','student_name')) || (!form['flea_market_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','contact_number')) || (!form['flea_market_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_details') || $check_field('add','item_details') || $check_field('set','item_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="物品详情" prop="item_details">
					<quill-editor v-model.number="form['item_details']"
						v-if="user_group === '管理员' || (form['flea_market_id'] && $check_field('set','item_details')) || (!form['flea_market_id'] && $check_field('add','item_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','item_details')" v-html="form['item_details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "flea_market_id",
				url_add: "~/api/flea_market/add?",
				url_set: "~/api/flea_market/set?",
				url_get_obj: "~/api/flea_market/get_obj?",
				url_upload: "~/api/flea_market/upload?",

				query: {
					"flea_market_id": 0,
				},

				form: {
					"item_name":'', // 物品名称
					"item_type":'', // 物品类型
					"item_picture":'', // 物品图片
					"article_fineness":'', // 物品成色
					"item_price":0, // 物品价格
					"owner": 0, // 物主
					"student_name":'', // 学生姓名
					"contact_number":'', // 联系电话
					"item_details":'', // 物品详情
					"flea_market_id": 0, // ID

				},
				disabledObj:{
					"item_name_isDisabled": false,
					"item_type_isDisabled": false,
					"item_picture_isDisabled": false,
					"article_fineness_isDisabled": false,
					"owner_isDisabled": false,
					"student_name_isDisabled": false,
					"contact_number_isDisabled": false,
					"item_details_isDisabled": false,
				},
				// 用户列表
				list_user_owner: [],
				// 用户组
				group_user_owner: "",
			}
		},
		methods: {
			/**
			 * 上传物品图片
			 * @param {Object} param图片参数
			 */
			upload_item_picture(param){
				this.uploadFile(param.file, "item_picture");
			},
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_owner() {
                // if(this.user_group !== "管理员" && this.form["owner"] === 0) {
                //     this.form["owner"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_owner = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户组
			 */
			async get_group_user_owner() {
				this.form["owner"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_owner = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_owner(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_owner.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="owner") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_owner(id){
				var obj = this.list_user_owner.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "物主":
							if(param["owner"] > 0){
								param["owner"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/flea_market/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/flea_market/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/flea_market/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/flea_market/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/flea_market/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_owner();
			this.get_group_user_owner();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
