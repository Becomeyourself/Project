<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import {ref, onMounted} from 'vue'

const Author = ref([
    {
        "id": 0,
        "name": "John Doe"
    },
    {
        "id": 2,
        "name": "Jane Smith"
    },
    {
        "id": 3,
        "name": "Albert Einstein"
    }
])

const AuthorModel = ref({
    'id' :'',
    'name' : ''
})

import {authorListService, authorAddService, authorDeleteService, authorUpdateService,authorPaperService} from '@/api/author.js'

//获取作者列表数据
const AuthorList = async () => {
    let result = await authorListService();

    //渲染视图
    Author.value = result.data;
}
AuthorList()

//定义变量,控制标题的展示
const title = ref('')

//清空模型的数据
const clearData = () => {
    AuthorModel.value.id = '';
    AuthorModel.value.name = '';
}

//添加作者表单校验
const rules = {
    name: [
        { required: true, message: '请输入作者名称', trigger: 'blur' },
    ]
}

//控制添加作者弹窗
const dialogVisible = ref(false)
import {ElMessage} from 'element-plus'

//添加作者
const addAuthor = async () => {
    //调用接口
    let result = await authorAddService(AuthorModel.value);

    ElMessage.success(result.msg? result.msg:'添加成功');

    //让弹窗消失
    dialogVisible.value = false;

    //刷新当前列表
    AuthorList()
}

//展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; title.value = '编辑作者'
    //数据拷贝
    AuthorModel.value.name = row.name;
    //扩展id属性,将来需要传递给后台,完成作者的修改
    AuthorModel.value.id = row.id
}

//更新作者
const updateAuthor = async () => {
    //调用接口
    let result = await authorUpdateService(AuthorModel.value);

    ElMessage.success(result.msg? result.msg:'修改成功');

    //让弹窗消失
    dialogVisible.value = false;
    clearData();
    //刷新当前列表
    AuthorList()
}

//删除作者
import {ElMessageBox} from 'element-plus'
const deleteAuthor = (row) => {
    //提示用户  确认框
    ElMessageBox.confirm(
        '你确认要删除该作者信息吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //调用接口
            let result = await authorDeleteService(row.id);
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            AuthorList()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '用户取消了删除',
            })
        })
}


const searchType = ref('');
import * as echarts from 'echarts'
const Search = async () => {
  try {
    let result = await authorPaperService(AuthorModel.value)
    if (result.code === 0) {
      ElMessage.success(result.msg? result.msg:'查询成功')
       drawChart(result.data)
    } else {
      ElMessage.error('搜索失败，请重试')
    }
  } catch (error) {
    ElMessage.error('搜索失败，请重试')
  }
}
const drawChart = (data) => {
  const chartDom = document.getElementById('chart');
  const myChart = echarts.init(chartDom);

  const option = {
    title: {
      text: '论文与作者关系图'
    },
    tooltip: {},
    series: [
      {
        type: 'graph',
        layout: 'force',
        force: {
          repulsion: 300, // 增加节点间距
          edgeLength: [50, 200]
        },
        roam: true, // 允许缩放和平移
        label: {
          show: true,
          position: 'right',
          formatter: '{b}'
        },
        data: [],
        links: [],
        lineStyle: {
          color: 'source',
          curveness: 0.3
        }
      }
    ]
  };

  const nodes = new Map();
  const links = [];

  for (const paper in data) {
    if (!nodes.has(paper)) {
      nodes.set(paper, { name: paper, category: 0, symbolSize: 70 });
    }
    data[paper].forEach((author) => {
      if (!nodes.has(author)) {
        nodes.set(author, { name: author, category: 1, symbolSize: 50 });
      }
      links.push({ source: paper, target: author });
    });
  }

  option.series[0].data = Array.from(nodes.values());
  option.series[0].links = links;

  myChart.setOption(option);
};


</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>作者列表</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = '添加作者'; clearData()">添加作者</el-button>
                </div>
            </div>
        </template>
        <!-- 搜索表单 -->
        <el-form inline>
            <!-- 下拉栏 -->
            <el-form-item>
                <el-select v-model="searchType" placeholder="论文关系网络搜索">
                    <el-option label="作者姓名" value="author"></el-option>
                </el-select>
                </el-form-item>
                
                <!-- 搜索内容输入框 -->
                <el-form-item>
                    <el-select v-model="AuthorModel.id" placeholder="请选择作者">
                <el-option v-for="j in Author" :key="j.id" :label="j.name" :value="j.id"></el-option>
                    </el-select>
                </el-form-item>

                <!-- 确认查询按钮 -->
                <el-form-item>
                    <el-button type="primary" @click="Search()">查询</el-button>
                </el-form-item>
            </el-form>
        <el-table :data="Author" style="width: 100%">
            <el-table-column label="序号" width="100" prop="id"> </el-table-column>
            <el-table-column label="名字" prop="name"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteAuthor(row)" ></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加作者弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="AuthorModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="序号" prop="id">
                    <el-input v-model="AuthorModel.id" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="作者名称" prop="name">
                    <el-input v-model="AuthorModel.name" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加作者' ? addAuthor() : updateAuthor()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
        <!-- 图表容器 -->
        <div id="chart" style="width: 600px; height: 400px;"></div>

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