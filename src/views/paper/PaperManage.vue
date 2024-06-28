<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

    import { ref } from 'vue'
    import { calService } from'@/api/cal.js'


    // 工作量分数存储对象
    const workloadScores = ref({});

    // 计算工作量分数的方法
    const calculateWorkloadScore = async (title, id) => {
        try {
            const result = await calService(title);
            if (result && result.data != null) {
                workloadScores.value[id] = result.data; // 更新指定行的计算结果
                ElMessage.success(`工作量分数: ${result.data}`); // 显示计算结果
            } else {
                ElMessage.error('计算失败，未返回有效数据');
            }
        } catch (error) {
            console.error('未上传文件:', error);
            ElMessage.error('未上传文件');
        }
    }

// 文章列表数据模型
const papers = ref([
    {
        id: 1,
        title: "Paper on Machine Learning",
        abstract: "Paper on Machine Learning",
        journalId: 1,
        categoryId: 1,
        filePath: "/path/to/machine_learning.pdf",
        publicationDate: "2024-01-01"

    },
    {
        id: 2,
        title: "Paper on Quantum Mechanics",
        abstract: "Paper on Quantum Mechanics",
        journalId: 2,
        categoryId: 2,
        filePath: "/path/to/quantum_mechanics.pdf",
        publicationDate: "2024-02-01"
    }
]);

const categorys = ref([]);
const Journal = ref([]);

// 定义变量, 控制标题的展示
const title = ref('')

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

// 清除模型的数据
const clearData = () => {
    paperModel.value.author1 = '';
    paperModel.value.author2 = '';
    paperModel.value.title = '';
    paperModel.value.abstractText = '';
    paperModel.value.journal = '';
    paperModel.value.category = '';
    paperModel.value.file_path = '';
    paperModel.value.publicationDate = '';
}

// 分页条数据模型
const pageNum = ref(1) // 当前页
const total = ref(20) // 总条数
const pageSize = ref(3) // 每页条数

// 当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    paperList()
}
// 当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    paperList()
}

import { paperListService, paperAddService, paperDeleteService, PaperUpdateService, authorFindService, categoryFindService, dateFindService, titleFindService, keywordFindService, journalFindService } from '@/api/paper.js';
    import { caterootService, cateseaService, categoryListService } from '@/api/category.js';
    import { journalListService } from '@/api/journal.js';
    import { pidService } from '@/api/author.js'
// 获取文章列表数据
const paperList = async () => {
    let result = await paperListService();

    papers.value = result.data;

    // 处理数据, 给数据模型扩展一个属性categoryName, 分类名称
    for (let i = 0; i < papers.value.length; i++) {
        let paper = papers.value[i];
        for (let j = 0; j < categorys.value.length; j++) {
            if (paper.categoryId == categorys.value[j].id) {
                paper.categoryName = categorys.value[j].name;
            }
        }
        // 处理数据, 给数据模型扩展一个属性JouranlName, 分类名称
        for (let k = 0; k < Journal.value.length; k++) {
            if (paper.journalId == Journal.value[k].id) {
                paper.journalName = Journal.value[k].name;
            }
        }
       
        try {
            let result = await pidService(paper.id);
            let author=result.data;
            console.log(author.value);

            if (author && author.length > 0) {
                paper.author1 = author[0]; // 设置第一个作者
                if (author.length > 1) {
                    paper.author2 = author[1]; // 如果有第二个作者，设置第二个作者
                } else {
                    paper.author2 = ''; // 如果没有第二个作者，清空可能存在的旧数据
                }
            } else {
                paper.author1 = ''; // 如果列表为空，清空作者1
                paper.author2 = ''; // 清空作者2
            }
        } catch (error) {
            console.error("Error fetching authors:", error);
            // 处理错误，可以设置作者为未知或者留空
            paper.author1 = '';
            paper.author2 = '';
        }
    }
}

const JournalList = async () => {
    let result = await journalListService();

    Journal.value = result.data;
}

const categoryList = async () => {
    let result = await categoryListService();

    categorys.value = result.data;
}

categoryList();
JournalList();
paperList();

import { ElMessage } from 'element-plus'
// 控制抽屉是否显示
const dialogVisible = ref(false)

// 添加三个级联下拉框的内容和选中的值
const categoryLevel1 = ref([]);
const categoryLevel2 = ref([]);
const categoryLevel3 = ref([]);
const selectedCategoryLevel1 = ref('');
const selectedCategoryLevel2 = ref('');
const selectedCategoryLevel3 = ref('');

// 初始化第一个下拉框的内容
const initCategoryLevel1 = async () => {
    try {
        const result = await caterootService();
        categoryLevel1.value = result.data;
    } catch (error) {
        console.error('获取一级分类失败:', error);
    }
}

const onCategoryLevel1Change = async (value) => {
    try {
        selectedCategoryLevel1.value = value;
        const result = await cateseaService(value);
        categoryLevel2.value = result.data;
        selectedCategoryLevel2.value = ''; // 清空第二级下拉框的选中值
        categoryLevel3.value = []; // 清空第三级下拉框的内容
        paperModel.value.category = value;
    } catch (error) {
        console.error('获取二级分类失败:', error);
    }
}

const onCategoryLevel2Change = async (value) => {
    try {
        selectedCategoryLevel2.value = value;
        const result = await cateseaService(value);
        categoryLevel3.value = result.data;
        selectedCategoryLevel3.value = ''; // 清空第三级下拉框的选中值
        paperModel.value.category = value;
    } catch (error) {
        console.error('获取三级分类失败:', error);
    }
}

const onCategoryLevel3Change = (value) => {
    selectedCategoryLevel3.value = value;
    paperModel.value.category = value;
}

// 展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; 
    title.value = '编辑论文';

    // 初始化第一个下拉框
    initCategoryLevel1();

    // 清空模型数据
    clearData();

    // 数据拷贝
    if (row) {
        paperModel.value.id = row.id;
        paperModel.value.title = row.title;
        paperModel.value.abstractText = row.abstract;
        paperModel.value.journal = row.journalName;
        paperModel.value.category = row.categoryName;
        paperModel.value.file_path = row.filePath;
        paperModel.value.publicationDate = row.publicationDate;
        paperModel.value.author1 = row.author1 || ''; // 如果没有提供作者1，则默认空字符串
        paperModel.value.author2 = row.author2 || ''; // 如果没有提供作者2，则默认空字符串
    }
    }

    const showDialog1 = () => {
        initCategoryLevel1();

        dialogVisible.value = true;
        // 初始化第一个下拉框
      
    }

const addPaper = async () => {
    // 调用接口
    let result = await paperAddService(paperModel.value);

    ElMessage.success(result.msg ? result.msg : '添加成功');

    // 让抽屉消失
    dialogVisible.value = false;

    // 刷新当前列表
    paperList()
}

const updatePaper = async () => {
    let result = await PaperUpdateService(paperModel.value);

    ElMessage.success(result.msg ? result.msg : '修改成功');

    // 让抽屉消失
    dialogVisible.value = false;

    // 刷新当前列表
    paperList()
}

// 删除分类
import { ElMessageBox } from 'element-plus'
const deletePaper = async (row) => {
    // 提示用户 确认框
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
        // 调用接口
        let result = await paperDeleteService(row.id);
        ElMessage({
            type: 'success',
            message: '删除成功',
        })
        // 刷新列表
        paperList()
    })
    .catch(() => {
        ElMessage({
            type: 'info',
            message: '用户取消了删除',
        })
    })  
}

// 导入token
import { useTokenStore } from '@/stores/token.js';
const tokenStore = useTokenStore();
// 查询操作
const searchType = ref('');
const searchContent = ref('');

const findPaper = async () => {
    let result;

    if (searchContent.value === "") {
        ElMessage.error('请输入有效的搜索内容');
        return;
    }

    try {
        switch (searchType.value) {
            case 'author':
                result = await authorFindService(searchContent.value);
                break;
            case 'title':
                result = await titleFindService(searchContent.value);
                break;
            case 'keyword':
                result = await keywordFindService(searchContent.value);
                break;
            case 'date':
                result = await dateFindService(searchContent.value);
                break;
            case 'journal':
                result = await journalFindService(searchContent.value);
                break;
            case 'category':
                result = await categoryFindService(searchContent.value);
                break;
            default:
                ElMessage.error('请选择一个有效的搜索条件');
                return;
        }
        ElMessage.success(result.msg ? result.msg : '查询成功');
        papers.value = result.data;
        for (let i = 0; i < papers.value.length; i++) {
            let paper = papers.value[i];
            for (let j = 0; j < categorys.value.length; j++) {
                if (paper.categoryId == categorys.value[j].id) {
                    paper.categoryName = categorys.value[j].name;
                }
            }
            // 处理数据, 给数据模型扩展一个属性JouranlName, 分类名称
            for (let k = 0; k < Journal.value.length; k++) {
                if (paper.journalId == Journal.value[k].id) {
                    paper.journalName = Journal.value[k].name;
                }
            }
        }
        return;
    } catch (error) {
        console.error('搜索失败:', error);
        ElMessage.error('搜索失败，请重试');
    }
}

// 导出到Excel功能的实现
import * as XLSX from 'xlsx'
const exportToExcel = () => {
    // 创建一个新的工作簿
    const wb = XLSX.utils.book_new()

    // 将数据转换为工作表
    const ws = XLSX.utils.json_to_sheet(papers.value.map(paper => ({
        '论文标题': paper.title,
        '简介': paper.abstract,
        '期刊': paper.journalName,
        '分类': paper.categoryName,
        '文件路径': paper.filePath,
        '日期': paper.publicationDate
    })))

    // 将工作表添加到工作簿
    XLSX.utils.book_append_sheet(wb, ws, '论文数据')

    // 生成 Excel 文件并触发下载
    XLSX.writeFile(wb, 'papers.xlsx')
}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>论文管理</span>
                <div class="extra">
                    <el-button type="primary" @click="showDialog1();title = '添加论文'; clearData()">添加论文</el-button>
                </div>
            </div>
        </template>

        <!-- 搜索表单 -->
        <el-form inline>
            <!-- 下拉栏 -->
            <el-form-item>
                <el-select v-model="searchType" placeholder="选择搜索条件">
                    <el-option label="按作者" value="author"></el-option>
                    <el-option label="按论文名称" value="title"></el-option>
                    <el-option label="按关键字" value="keyword"></el-option>
                    <el-option label="按时间" value="date"></el-option>
                    <el-option label="按刊物" value="journal"></el-option>
                    <el-option label="按类别" value="category"></el-option>
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
            
            <!-- 导出到Excel按钮 -->
            <el-form-item>
                <el-button type="success" @click="exportToExcel()">导出到Excel</el-button>
            </el-form-item>
        </el-form>

        <!-- 文章列表 -->
        <el-table :data="papers" style="width: 100%">
            <el-table-column label="论文标题" width="400" prop="title"></el-table-column>
            <el-table-column label="简介" prop="abstract"></el-table-column>
            <el-table-column label="期刊" prop="journalName"></el-table-column>
            <el-table-column label="分类" prop="categoryName"></el-table-column>
            <!-- <el-table-column label="文件路径" prop="filePath"></el-table-column>-->
            <el-table-column label="作者1" prop="author1"></el-table-column>
            <el-table-column label="作者2" prop="author2"></el-table-column>
            <el-table-column label="日期" prop="publicationDate"></el-table-column>
            <el-table-column label="工作量分数" width="200">
                <template #default="{ row }">
                    <el-input v-model="workloadScores[row.id]" placeholder="计算分数" disabled></el-input>
                    <el-button type="primary" @click="calculateWorkloadScore(row.title, row.id)">计算</el-button>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deletePaper(row)"></el-button>
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
        <el-dialog v-model="dialogVisible" :title="title" width="50%">
            <!-- 添加论文表单 -->
            <el-form :model="paperModel" label-width="100px">
                <el-form-item label="标题">
                    <el-input v-model="paperModel.title" placeholder="请输入标题"></el-input>
                </el-form-item>

                <!-- 级联下拉框 -->
                <el-form-item label="分类">
                    <el-select v-model="selectedCategoryLevel1" placeholder="请选择一级分类" @change="onCategoryLevel1Change">
                        <el-option v-for="item in categoryLevel1" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                    <el-select v-model="selectedCategoryLevel2" placeholder="请选择二级分类" @change="onCategoryLevel2Change" v-if="categoryLevel2.length > 0">
                        <el-option v-for="item in categoryLevel2" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                    <el-select v-model="selectedCategoryLevel3" placeholder="请选择三级分类" @change="onCategoryLevel3Change" v-if="categoryLevel3.length > 0">
                        <el-option v-for="item in categoryLevel3" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>

                <!-- 其他表单项 -->
                <el-form-item label="简介">
                    <el-input v-model="paperModel.abstractText" placeholder="请输入简介"></el-input>
                </el-form-item>
                <el-form-item label="期刊">
                    <el-select v-model="paperModel.journal" placeholder="请选择期刊">
                        <el-option v-for="j in Journal" :key="j.id" :label="j.name" :value="j.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="路径">
                    <el-input v-model="paperModel.file_path" placeholder="请输入路径"></el-input>
                </el-form-item>
                <el-form-item label="日期">
                    <el-date-picker
                        v-model="paperModel.publicationDate"
                        type="date"
                        placeholder="请选择日期"
                        format="YYYY-MM-DD"
                        value-format="YYYY-MM-DD">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="作者1">
                    <el-input v-model="paperModel.author1" placeholder="请输入作者1"></el-input>
                </el-form-item>
                <el-form-item label="作者2">
                    <el-input v-model="paperModel.author2" placeholder="请输入作者2"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加论文' ? addPaper() : updatePaper()"> 确认 </el-button>
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

/* 抽屉样式 */
.avatar-uploader {
    ::v-deep .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    ::v-deep .el-upload {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    ::v-deep .el-upload:hover {
        border-color: var(--el-color-primary);
    }

    ::v-deep .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }
}

.editor {
    width: 100%;

    ::v-deep .ql-editor {
        min-height: 200px;
    }
}
</style>
