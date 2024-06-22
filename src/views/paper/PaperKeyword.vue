<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import { ref } from 'vue'

const Keywords = ref([
    {
        "id": 0,
        "keyword": "Machine Learning"
    },
    {
        "id": 2,
        "keyword": "Artificial Intelligence"
    },
    {
        "id": 3,
        "keyword": "Data Science"
    }
])

const KeywordModel = ref({
    'id': '',
    'keyword': ''
})

import { keywordListService, keywordAddService, keywordDeleteService, keywordUpdateService } from '@/api/keyword.js'

// 获取关键词列表数据
const KeywordList = async () => {
    let result = await keywordListService();

    // 渲染视图
    Keywords.value = result.data;
}
KeywordList()

// 定义变量,控制标题的展示
const title = ref('')

// 清空模型的数据
const clearData = () => {
    KeywordModel.value.id = '';
    KeywordModel.value.keyword = '';
}

// 添加关键词表单校验
const rules = {
    keyword: [
        { required: true, message: '请输入关键词', trigger: 'blur' },
    ]
}

// 控制添加关键词弹窗
const dialogVisible = ref(false)
import { ElMessage } from 'element-plus'

// 添加关键词
const addKeyword = async () => {
    // 调用接口
    let result = await keywordAddService(KeywordModel.value);

    ElMessage.success(result.msg ? result.msg : '添加成功');

    // 让弹窗消失
    dialogVisible.value = false;

    // 刷新当前列表
    KeywordList()
}

// 展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; title.value = '编辑关键词'
    // 数据拷贝
    KeywordModel.value.keyword = row.keyword;
    // 扩展id属性,将来需要传递给后台,完成关键词的修改
    KeywordModel.value.id = row.id
}

// 更新关键词
const updateKeyword = async () => {
    // 调用接口
    let result = await keywordUpdateService(KeywordModel.value);

    ElMessage.success(result.msg ? result.msg : '修改成功');

    // 让弹窗消失
    dialogVisible.value = false;

    // 刷新当前列表
    KeywordList()
}

// 删除关键词
import { ElMessageBox } from 'element-plus'
const deleteKeyword = (row) => {
    // 提示用户 确认框
    ElMessageBox.confirm(
        '你确认要删除该关键词吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            // 调用接口
            let result = await keywordDeleteService(row.id);
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            // 刷新列表
            KeywordList()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '用户取消了删除',
            })
        })
}

</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>关键词管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = '添加关键词'; clearData()">添加关键词</el-button>
                </div>
            </div>
        </template>
        <el-table :data="Keywords" style="width: 100%">
            <el-table-column label="序号" width="100" prop="id"></el-table-column>
            <el-table-column label="关键词" prop="keyword"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteKeyword(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加关键词弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="KeywordModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="序号" prop="id">
                    <el-input v-model="KeywordModel.id" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="关键词" prop="keyword">
                    <el-input v-model="KeywordModel.keyword" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加关键词' ? addKeyword() : updateKeyword()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>
