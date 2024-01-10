<template>
  <div>
    <el-card style="width: 50%">
      <el-form :model="user" label-width="100px" style="padding-right: 50px">
        <div style="margin: 15px; text-align: center">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <el-form-item label="商家名称" prop="name">
            <el-input v-model="user.name" placeholder="商家名称"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="username">
            <el-input v-model="user.username" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="user.phone" placeholder="电话"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="info">
            <el-input type="textarea" v-model="user.info" placeholder="简介"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input type="textarea" v-model="user.address" placeholder="地址"></el-input>
          </el-form-item>
          <el-form-item label="营业时间">
            <el-row>
              <el-col :span="11">
                <el-form-item  style="width: 100%">
                  <el-time-select
                      style="width: auto"
                      v-model="user.startTime"
                      :picker-options="{start: '06:00',step: '00:30',end: '24:00'}"
                      placeholder="开始时间">
                  </el-time-select>
                </el-form-item>
              </el-col>
              <el-col :span="2"><div>&ensp;至</div></el-col>
              <el-col :span="11">
                <el-form-item style="width: 100%">
                  <el-time-select
                      style="width: auto"
                      v-model="user.endTime"
                      :picker-options="{start: '08:30',step: '00:15',end: '24:00'}"
                      placeholder="结束时间">
                  </el-time-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="类型" prop="type">
            <el-select style="width: 100%" v-model="user.type">
              <el-option v-for="item in ['奶茶饮品', '快餐简食', '炸鸡汉堡', '特色美食']" :key="item" :value="item" :label="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="营业执照">
            <el-upload
            :action="$baseUrl + '/files/upload'"
            :show-file-list="false"
            :on-success="handleLicenseSuccess"
            >
              <img style="object-fit: cover" v-if="user.license" :src="user.license" class="license"/>
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>

        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "BusinessPerson",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}')
    }
  },
  created() {

  },
  methods: {
    handleLicenseSuccess(response, file, fileList) {
      this.user.license = response.data
    },
    update() {
      // 保存当前的用户信息到数据库
      this.$request.put('/business/update', this.user).then(res => {
        if (res.code === '200') {
          // 成功更新
          this.$message.success('保存成功')

          // 更新浏览器缓存里的用户信息
          localStorage.setItem('xm-user', JSON.stringify(this.user))

          // 触发父级的数据更新
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.$set(this.user, 'avatar', response.data)
    },
  }
}
</script>

<style scoped>
/deep/.el-form-item__label {
  font-weight: bold;
}
/deep/.el-upload {
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
.license {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>