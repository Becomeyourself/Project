<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import {ref} from'vue'

const categorys = ref([
    {
        "id": 0,
        "parentId": 2,
        "name": "计算机"
    },
    {
        "id": 2,
        "parentId": 3,
        "name": "天文学"
    },
    {
        "id": 3,
        "parentId": 0,
        "name": "地质学"
    }
])

const categoryModel = ref({
    "id": "",
    "parentId": "",
    "name": ""
})

import {categoryListService,categoryAddService,categoryDeleteService,categoryUpdateService} from '@/api/category.js'
//获取文章分类列表数据
const categoryList = async () => {
    let result = await categoryListService();

    //渲染视图
    categorys.value = result.data;
}

categoryList();
//定义变量,控制标题的展示
const title = ref('')

//清空模型的数据
const clearData = () => {
    categoryModel.value.parentId = '';
    categoryModel.value.name = '';
}

//添加分类表单校验
const rules = {
    parentId: [
        { required: true, message: '请输入父节点', trigger: 'blur' },
    ],
    name: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ]
}
//控制添加分类弹窗
const dialogVisible = ref(false)

import {ElMessage} from 'element-plus'
//添加分类
const addCategory = async ()=>{
    //调用接口
    let result = await categoryAddService(categoryModel.value);

    ElMessage.success(result.msg? result.msg:'添加成功');

    //让弹窗消失
    dialogVisible.value = false;

    //刷新当前列表
    categoryList()
}

//展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; title.value = '编辑分类'
    //数据拷贝
    categoryModel.value.parentId = row.parentId;
    categoryModel.value.name = row.name;
    //扩展id属性,将来需要传递给后台,完成分类的修改
    categoryModel.value.id = row.id
}
//更新分类
const updateCategory = async() =>{
    //调用接口
    let result = await categoryUpdateService(categoryModel.value);

    ElMessage.success(result.msg? result.msg:'修改成功');

    //让弹窗消失
    dialogVisible.value = false;

    //刷新当前列表
    categoryList()
}
//删除分类
import {ElMessageBox} from 'element-plus'
const deleteCategory = (row) => {
    //提示用户  确认框

    ElMessageBox.confirm(
        '你确认要删除该分类信息吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //调用接口
            let result = await categoryDeleteService(row.id);
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            categoryList()
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
                <span>论文分类</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = '添加分类'; clearData()">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" prop="id"> </el-table-column>
            <el-table-column label="父节点" prop="parentId"></el-table-column>
            <el-table-column label="名字" prop="name"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)" ></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="父节点" prop="parentId">
                    <el-input v-model="categoryModel.parentId" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类名称" prop="name">
                    <el-input v-model="categoryModel.name" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加分类' ? addCategory() : updateCategory()"> 确认 </el-button>
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