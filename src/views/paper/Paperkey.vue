<script setup>
    import { ref } from 'vue';
    import { ElMessage } from 'element-plus';
    import { keywordListService, paperListService, addService, deleService } from '@/api/key.js'; // 修改为正确的服务接口

    // 关键词列表数据模型
    const items = ref([]);

    // 搜索相关变量
    const searchType = ref('title');
    const searchContent = ref('');

    // 编辑界面的变量
    const dialogVisible = ref(false);
    const deleteDialogVisible = ref(false);
    const paperTitle = ref('');
    const keyword1 = ref('');
    const keyword2 = ref('');
    const keyword3 = ref('');
    const deletePaperTitle = ref('');
    const deleteKeyword = ref('');

    // 搜索函数
    const findPaper = async () => {
        if (searchContent.value === "") {
            ElMessage.error('请输入有效的搜索内容');
            return;
        }

        try {
            let result;
            if (searchType.value === 'title') {
                result = await keywordListService(searchContent.value);
            } else {
                result = await paperListService(searchContent.value);
            }

            if (result && result.data != null) {
                items.value = result.data.map((item, index) => ({
                    id: index + 1,
                    content: item
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

    // 打开添加编辑界面
    const openDialog = () => {
        dialogVisible.value = true;
        paperTitle.value = '';
        keyword1.value = '';
        keyword2.value = '';
        keyword3.value = '';
    };

    // 打开删除编辑界面
    const openDeleteDialog = () => {
        deleteDialogVisible.value = true;
        deletePaperTitle.value = '';
        deleteKeyword.value = '';
    };

    // 添加功能
    const addPaper = async () => {
        try {
            let results = [];
            if (keyword1.value) {
                results.push(await addService(paperTitle.value, keyword1.value));
            }
            if (keyword2.value) {
                results.push(await addService(paperTitle.value, keyword2.value));
            }
            if (keyword3.value) {
                results.push(await addService(paperTitle.value, keyword3.value));
            }

            const success = results.every(result => result && result.code === 0);

            if (success) {
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

    // 删除功能
    const deletePaper = async () => {
        try {
            const result = await deleService(deletePaperTitle.value, deleteKeyword.value);

            if (result && result.code === 0) {
                ElMessage.success('删除成功');
                deleteDialogVisible.value = false;
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
        items.value = [];
        searchContent.value = '';
        searchType.value = 'title';
    };

    // 获取关键词列表数据的初始函数
    const keywordList = async () => {
        // 初始获取所有关键词数据的函数实现可以放这里
    };

    keywordList();
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>关键词管理</span>
                <div class="button-group">
                    <el-button type="primary" @click="openDialog">增加</el-button>
                    <el-button type="danger" @click="openDeleteDialog">删除</el-button>
                </div>
            </div>
        </template>

        <!-- 搜索表单 -->
        <el-form inline>
            <!-- 下拉栏 -->
            <el-form-item>
                <el-select v-model="searchType" placeholder="选择搜索条件">
                    <el-option label="按论文名称" value="title"></el-option>
                    <el-option label="按关键词" value="keyword"></el-option>
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

        <!-- 关键词列表 -->
        <el-table :data="items" style="width: 100%">
            <el-table-column v-if="searchType === 'title'"
                             label="关键词"
                             width="200"
                             prop="content"></el-table-column>
            <el-table-column v-else
                             label="论文标题"
                             width="400"
                             prop="content"></el-table-column>
        </el-table>

        <template #empty>
            <el-empty description="没有数据" />
        </template>

        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum"
                       v-model:page-size="pageSize"
                       :page-sizes="[3, 5, 10, 15]"
                       layout="jumper, total, sizes, prev, pager, next"
                       background
                       :total="total"
                       @size-change="onSizeChange"
                       @current-change="onCurrentChange"
                       style="margin-top: 20px; justify-content: flex-end" />

        <!-- 添加编辑界面 -->
        <el-dialog v-model="dialogVisible"
                   title="增加论文和关键词"
                   width="30%">
            <el-form>
                <el-form-item label="论文">
                    <el-input v-model="paperTitle" placeholder="请输入论文名称"></el-input>
                </el-form-item>
                <el-form-item label="关键词1">
                    <el-input v-model="keyword1" placeholder="请输入关键词1"></el-input>
                </el-form-item>
                <el-form-item label="关键词2">
                    <el-input v-model="keyword2" placeholder="请输入关键词2"></el-input>
                </el-form-item>
                <el-form-item label="关键词3">
                    <el-input v-model="keyword3" placeholder="请输入关键词3"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addPaper">确认</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 删除编辑界面 -->
        <el-dialog v-model="deleteDialogVisible"
                   title="删除论文和关键词"
                   width="30%">
            <el-form>
                <el-form-item label="论文">
                    <el-input v-model="deletePaperTitle" placeholder="请输入论文名称"></el-input>
                </el-form-item>
                <el-form-item label="关键词">
                    <el-input v-model="deleteKeyword" placeholder="请输入关键词"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="deleteDialogVisible = false">取消</el-button>
                    <el-button type="danger" @click="deletePaper">确认</el-button>
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
