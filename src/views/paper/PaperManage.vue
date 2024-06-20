<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import { ref } from 'vue'

//文章列表数据模型
// 文章列表数据模型
const papers = ref([
    {
        id: 1,
        title: "Paper on Machine Learning",
        abstractText: "",
        journalId: 1,
        categoryId: 1,
        filePath: "/path/to/machine_learning.pdf",
        publicationDate: "2024-01-01"
    },
    {
        id: 2,
        title: "Paper on Quantum Mechanics",
        abstractText: "",
        journalId: 2,
        categoryId: 2,
        filePath: "/path/to/quantum_mechanics.pdf",
        publicationDate: "2024-02-01"
    }
]);


const paperModel = ref({
    "author1" : "",
    "author2" : "",
    "title" : "",
    "abstractText":"",
    "journal":"",
    "category":"",
    "file_path":"",
    "publicationDate":""
})
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数

//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    paperList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    paperList()
}

import {paperListService,paperAddService} from '@/api/paper.js'
//获取文章列表数据
const paperList = async () => {
    let result = await paperListService();

    //渲染视图
    papers.value = result.data;
}

paperList();

import {ElMessage} from 'element-plus'
//控制抽屉是否显示
const visibleDrawer = ref(false)
const addpaper = async ()=>{
    //调用接口
    let result = await paperAddService(paperModel.value);

    ElMessage.success(result.msg? result.msg:'添加成功');

    //让抽屉消失
    visibleDrawer.value = false;

    //刷新当前列表
    paperList()
}
//导入token
import { useTokenStore } from '@/stores/token.js';
const tokenStore = useTokenStore();

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>论文管理</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer = true">添加论文</el-button>
                </div>
            </div>
        </template>
        <!-- 搜索表单 -->
        <el-form inline>

        </el-form>
        <!-- 文章列表 -->
            <el-table :data="papers" style="width: 100%">
                <el-table-column label="论文标题" width="400" prop="title"></el-table-column>
                <el-table-column label="简介" prop="abstract"></el-table-column>
                <el-table-column label="期刊" prop="journalId"> </el-table-column>
                <el-table-column label="分类" prop="categoryId"></el-table-column>
                <el-table-column label="文件路径" prop="filePath"></el-table-column>
                <el-table-column label="日期" prop="publicationDate"></el-table-column>
                <el-table-column label="操作" width="100">
                    <template #default="{ row }">
                        <el-button :icon="Edit" circle plain type="primary"></el-button>
                        <el-button :icon="Delete" circle plain type="danger"></el-button>
                    </template>
                </el-table-column>
                <template #empty>
                    <el-empty description="没有数据" />
                </template>
            </el-table>

        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />

        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" title="添加论文" direction="rtl" size="50%">
            <!-- 添加论文表单 -->
            <el-form :model="paperModel" label-width="100px">
                <el-form-item label="标题">
                    <el-input v-model="paperModel.title" placeholder="请输入标题"></el-input>
                </el-form-item>
                <el-form-item label="分类">
                    <el-input v-model="paperModel.category" placeholder="请输入分类"></el-input>
                </el-form-item>
                <el-form-item label="简介">
                    <el-input v-model="paperModel.abstractText" placeholder="请输入简介"></el-input>
                </el-form-item>
                <el-form-item label="期刊">
                    <el-input v-model="paperModel.journal" placeholder="请输入期刊"></el-input>
                </el-form-item>
                <el-form-item label="路径">
                    <el-input v-model="paperModel.file_path" placeholder="请输入路径"></el-input>
                </el-form-item>
                <el-form-item label="日期">
                    <el-input v-model="paperModel.publicationDate" placeholder="请输入日期"></el-input>
                </el-form-item>
                <el-form-item label="作者1">
                    <el-input v-model="paperModel.author1" placeholder="请输入作者1"></el-input>
                </el-form-item>
                <el-form-item label="作者2">
                    <el-input v-model="paperModel.author2" placeholder="请输入作者2"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addpaper()" >添加</el-button>
                </el-form-item>
            </el-form>    
        </el-drawer>
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

/* 抽屉样式 */
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}

.editor {
    width: 100%;

    :deep(.ql-editor) {
        min-height: 200px;
    }
}
</style>