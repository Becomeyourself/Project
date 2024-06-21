<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import {ref} from'vue'

const Journal = ref([
    {
        "id": 0,
        "name": "ACM"
    },
    {
        "id": 2,
        "name": "Nature"
    },
    {
        "id": 3,
        "name": "Science"
    }
])

const JournalModel = ref({
    'id' :'',
    'name' : ''
})
import {journalListService,journalAddService,journalDeleteService,journalUpdateService} from '@/api/journal.js'

//获取文章分类列表数据
const JournalList = async () => {
    let result = await journalListService();

    //渲染视图
    Journal.value = result.data;
}
JournalList()
//定义变量,控制标题的展示
const title = ref('')

//清空模型的数据
const clearData = () => {
    JournalModel.value.Id = '';
    JournalModel.value.name = '';
}

//添加分类表单校验
const rules = {
    name: [
        { required: true, message: '请输入期刊名称', trigger: 'blur' },
    ]
}
//控制添加分类弹窗
const dialogVisible = ref(false)
import {ElMessage} from 'element-plus'
//添加分类
const addJournal = async ()=>{
    //调用接口
    let result = await journalAddService(JournalModel.value);

    ElMessage.success(result.msg? result.msg:'添加成功');

    //让弹窗消失
    dialogVisible.value = false;

    //刷新当前列表
    JournalList()
}
//展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; title.value = '编辑期刊'
    //数据拷贝
    JournalModel.value.name = row.name;
    //扩展id属性,将来需要传递给后台,完成分类的修改
    JournalModel.value.id = row.id
}
//更新分类
const updateJournal = async() =>{
    //调用接口
    let result = await journalUpdateService(JournalModel.value);

    ElMessage.success(result.msg? result.msg:'修改成功');

    //让弹窗消失
    dialogVisible.value = false;

    //刷新当前列表
    JournalList()
}
//删除分类
import {ElMessageBox} from 'element-plus'
const deleteJournal = (row) => {
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
            let result = await journalDeleteService(row.id);
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            JournalList()
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
                <span>论文期刊</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = '添加期刊'; clearData()">添加期刊</el-button>
                </div>
            </div>
        </template>
        <el-table :data="Journal" style="width: 100%">
            <el-table-column label="序号" width="100" prop="id"> </el-table-column>
            <el-table-column label="名字" prop="name"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteJournal(row)" ></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="JournalModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="序号" prop="Id">
                    <el-input v-model="JournalModel.Id" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="期刊名称" prop="name">
                    <el-input v-model="JournalModel.name" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加期刊' ? addJournal() : updateJournal()"> 确认 </el-button>
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