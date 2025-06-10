<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','take_goods_on_behalf_of_others') || $check_field('add','take_goods_on_behalf_of_others') || $check_field('set','take_goods_on_behalf_of_others')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="代拿物品" prop="take_goods_on_behalf_of_others">
					<el-input id="take_goods_on_behalf_of_others" v-model="form['take_goods_on_behalf_of_others']" placeholder="请输入代拿物品"
							  v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','take_goods_on_behalf_of_others')) || (!form['with_running_service_id'] && $check_field('add','take_goods_on_behalf_of_others'))" :disabled="disabledObj['take_goods_on_behalf_of_others_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','take_goods_on_behalf_of_others')">{{form['take_goods_on_behalf_of_others']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_location') || $check_field('add','item_location') || $check_field('set','item_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品位置" prop="item_location">
					<el-input id="item_location" v-model="form['item_location']" placeholder="请输入物品位置"
							  v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','item_location')) || (!form['with_running_service_id'] && $check_field('add','item_location'))" :disabled="disabledObj['item_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_location')">{{form['item_location']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','receiving_address') || $check_field('add','receiving_address') || $check_field('set','receiving_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收取地址" prop="receiving_address">
					<el-input id="receiving_address" v-model="form['receiving_address']" placeholder="请输入收取地址"
							  v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','receiving_address')) || (!form['with_running_service_id'] && $check_field('add','receiving_address'))" :disabled="disabledObj['receiving_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','receiving_address')">{{form['receiving_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_students') || $check_field('add','release_students') || $check_field('set','release_students')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布学生" prop="release_students">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_release_students(form['release_students']) }}
							<!--<el-input id="business_name" v-model="form['release_students']" placeholder="请输入发布学生"-->
							<!--v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','release_students')) || (!form['with_running_service_id'] && $check_field('add','release_students'))" :disabled="disabledObj['release_students_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','release_students')">{{form['release_students']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','release_students')) || (!form['with_running_service_id'] && $check_field('add','release_students'))" id="release_students" v-model="form['release_students']" :disabled="disabledObj['release_students_isDisabled']">
								<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','release_students')" id="release_students" v-model="form['release_students']" :disabled="true">
								<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="release_students" v-model="form['release_students']" :disabled="disabledObj['release_students_isDisabled']">
							<el-option v-for="o in list_user_release_students" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','student_name')) || (!form['with_running_service_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','contact_number')) || (!form['with_running_service_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','running_expenses') || $check_field('add','running_expenses') || $check_field('set','running_expenses')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="带跑费用" prop="running_expenses">
					<el-input-number id="running_expenses" v-model.number="form['running_expenses']"
						v-if="user_group === '管理员' || (form['with_running_service_id'] && $check_field('set','running_expenses')) || (!form['with_running_service_id'] && $check_field('add','running_expenses'))"></el-input-number>
					<div v-else-if="$check_field('get','running_expenses')">{{form['running_expenses']}}</div>
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
				field: "with_running_service_id",
				url_add: "~/api/with_running_service/add?",
				url_set: "~/api/with_running_service/set?",
				url_get_obj: "~/api/with_running_service/get_obj?",
				url_upload: "~/api/with_running_service/upload?",

				query: {
					"with_running_service_id": 0,
				},

				form: {
					"take_goods_on_behalf_of_others":'', // 代拿物品
					"item_location":'', // 物品位置
					"receiving_address":'', // 收取地址
					"release_students": 0, // 发布学生
					"student_name":'', // 学生姓名
					"contact_number":'', // 联系电话
					"running_expenses":0, // 带跑费用
					"with_running_service_id": 0, // ID

				},
				disabledObj:{
					"take_goods_on_behalf_of_others_isDisabled": false,
					"item_location_isDisabled": false,
					"receiving_address_isDisabled": false,
					"release_students_isDisabled": false,
					"student_name_isDisabled": false,
					"contact_number_isDisabled": false,
				},
				// 用户列表
				list_user_release_students: [],
				// 用户组
				group_user_release_students: "",
			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_release_students() {
                // if(this.user_group !== "管理员" && this.form["release_students"] === 0) {
                //     this.form["release_students"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_release_students = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户组
			 */
			async get_group_user_release_students() {
				this.form["release_students"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_release_students = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_release_students(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_release_students.source_table+"/get_obj?"
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
									if (arr[i]!=="release_students") {
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
			get_user_release_students(id){
				var obj = this.list_user_release_students.getObj({"user_id":id});
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
						case "发布学生":
							if(param["release_students"] > 0){
								param["release_students"] = this.user.user_id;
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
					bl = this.$check_action('/with_running_service/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/with_running_service/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/with_running_service/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/with_running_service/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/with_running_service/view','get');
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
			this.get_list_user_release_students();
			this.get_group_user_release_students();
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
