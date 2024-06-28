<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
    import { sealistService, searefeService, addService, deleService } from '@/api/refe.js'; // 引入 sealistService 和 searefeService 接口

// 文章列表数据模型
const papers = ref([]);

// 搜索相关变量
const searchType = ref('title');
const searchContent = ref('');

// 编辑界面的变量
    const dialogVisible = ref(false);
    const dialogVisible1 = ref(false);
const paperTitle = ref('');
const referenceText = ref('');

// 搜索函数
const findPaper = async () => {
    let result;

    if (searchContent.value === "") {
        ElMessage.error('请输入有效的搜索内容');
        return;
    }

    try {
        if (searchType.value === 'title') {
            result = await sealistService(searchContent.value);
        } else if (searchType.value === 'reference') {
            result = await searefeService(searchContent.value);
        }

        if (result && result.data != null) {
            papers.value = result.data.map((title, index) => ({
                id: index + 1,
                title,
            }));
            ElMessage.success('查询成功');
        } else {
            ElMessage.error('未找到相关结果');
        }
    } catch (error) {
        console.error('搜索失败:', error);
        ElMessage.error('搜索失败，请重试');
    }
};

// 打开编辑界面
const openDialog = () => {
    dialogVisible.value = true;
    paperTitle.value = '';
    referenceText.value = '';
    };

    const openDialog1 = () => {
        dialogVisible1.value = true;
        paperTitle.value = '';
        referenceText.value = '';
    };

// 添加功能
const addPaper = async () => {
    try {
        const result = await addService(paperTitle.value, referenceText.value);
        console.log(result.code);
        paperTitle.value = '';
        referenceText.value = '';
        if (result && result.code === 0) {
            
            ElMessage.success('添加成功');
            dialogVisible.value = false;
        } else {
            ElMessage.error('添加失败');
        }
    } catch (error) {
        console.error('添加失败:', error);
        ElMessage.error('添加失败，请重试');
    }
    };

    const delePaper = async () => {
        try {
            const result = await deleService(paperTitle.value, referenceText.value);
            console.log(result.code);
            paperTitle.value = '';
            referenceText.value = '';
            if (result && result.code === 0) {

                ElMessage.success('删除成功');
                dialogVisible1.value = false;
            } else {
                ElMessage.error('删除失败');
            }
        } catch (error) {
            console.error('删除失败:', error);
            ElMessage.error('删除失败，请重试');
        }
    };

// 复位函数
const resetTable = () => {
    papers.value = [];
    searchContent.value = '';
    searchType.value = 'title';
};

// 获取文章列表数据的初始函数
const paperList = async () => {
    // 初始获取所有文章数据的函数实现可以放这里
};

paperList();
</script>




<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>参考文献</span>
                <div class="button-group">
                    <el-button type="primary" @click="openDialog">增加</el-button>
                    <el-button type="danger" @click="openDialog1">删除</el-button>
                </div>
            </div>
        </template>

        <!-- 搜索表单 -->
        <el-form inline>
            <!-- 下拉栏 -->
            <el-form-item>
                <el-select v-model="searchType" placeholder="选择搜索条件">
                    <el-option label="按论文名称" value="title"></el-option>
                    <el-option label="按参考文献" value="reference"></el-option>
                </el-select>
            </el-form-item>

            <!-- 搜索内容输入框 -->
            <el-form-item>
                <el-input v-model="searchContent" placeholder="请输入搜索内容"></el-input>
            </el-form-item>

            <!-- 确认查询按钮 -->
            <el-form-item>
                <el-button type="primary" @click="findPaper()">查询</el-button>
            </el-form-item>

            <!-- 复位按钮 -->
            <el-form-item>
                <el-button type="warning" @click="resetTable">复位</el-button>
            </el-form-item>
        </el-form>

        <!-- 文章列表 -->
        <el-table :data="papers" style="width: 100%">
            <el-table-column label="论文标题" width="400" prop="title"></el-table-column>
        </el-table>

        <template #empty>
            <el-empty description="没有数据" />
        </template>

        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
                       layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
                       @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />

        <!-- 编辑界面 -->
        <el-dialog v-model="dialogVisible" title="增加论文和参考文献" width="30%">
            <el-form>
                <el-form-item label="论文">
                    <el-input v-model="paperTitle" placeholder="请输入论文名称"></el-input>
                </el-form-item>
                <el-form-item label="参考文献">
                    <el-input v-model="referenceText" placeholder="请输入参考文献"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addPaper">确认</el-button>
                </span>
            </template>
        </el-dialog>

        <el-dialog v-model="dialogVisible1" title="删除论文和参考文献" width="30%">
            <el-form>
                <el-form-item label="论文">
                    <el-input v-model="paperTitle" placeholder="请输入论文名称"></el-input>
                </el-form-item>
                <el-form-item label="参考文献">
                    <el-input v-model="referenceText" placeholder="请输入参考文献"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible1 = false">取消</el-button>
                    <el-button type="primary" @click="delePaper">确认</el-button>
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

        .button-group {
            display: flex;
            gap: 10px;
        }
    }
}

/* 表格样式 */
.editor {
    width: 100%;

    ::v-deep .ql-editor {
        min-height: 200px;
    }
}
</style>

