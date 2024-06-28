<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { uploadService, downService } from '@/api/paper.js'; // 引入定义好的上传服务
import axios from 'axios';

const file = ref(null);
const papername = ref('');
const downloadName = ref('');

const handleFileChange = (event) => {
    file.value = event.target.files[0];
};

const uploadFile = async () => {
    if (file.value && papername.value) {
        try {
            const formData = new FormData();
            formData.append('file', file.value);
            formData.append('papername', papername.value);

            const response = await uploadService(formData);
            console.log('Upload successful:', response);
            ElMessage.success('Upload successful');
        } catch (error) {
            console.error('Upload failed:', error);
            ElMessage.error('Upload failed');
        }
    } else {
        ElMessage.error('Please select a file and enter a paper name.');
    }
};

const downloadFile = async () => {
        if (downloadName.value) {
            try {
                const response = await downService(downloadName.value);
                const { fileType, fileData } = response.data; // 获取文件类型和Base64编码数据
                
                // Base64解码
                const binaryString = atob(fileData);
                const byteArray = new Uint8Array(binaryString.length);

                for (let i = 0; i < binaryString.length; i++) {
                    byteArray[i] = binaryString.charCodeAt(i);
                }

                // 根据文件类型生成Blob
                const blob = new Blob([byteArray], { type: fileType });
                const url = window.URL.createObjectURL(blob);

                let fileExtension = '';
                if (fileType.includes('plain')) {
                    fileExtension = 'txt';
                } else if (fileType.includes('msword')) {
                    fileExtension = 'doc';
                } else if (fileType.includes('pdf')) {
                    fileExtension = 'pdf';
                }

                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', `${downloadName.value}.${fileExtension}`); // 设定文件下载名
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
                ElMessage.success('Download successful');
            } catch (error) {
                console.error('Download failed:', error);
                ElMessage.error('Download failed');
            }
        } else {
            alert('Please enter a paper name.');
        }
    };

</script>

<template>
    <div class="page-container">
        <div class="header">
            <h1>Upload and Download Papers</h1>
        </div>
        <div class="upload-demo">
            <input type="file" @change="handleFileChange" class="upload-input" />
            <input type="text" v-model="papername" placeholder="Enter paper name" class="text-input" />
            <button @click="uploadFile" class="upload-button">Upload</button>
        </div>
        <div class="download-demo">
            <input type="text" v-model="downloadName" placeholder="Enter paper name for download" class="text-input" />
            <button @click="downloadFile" class="download-button">Download</button>
        </div>
    </div>
</template>

<style scoped>
.page-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    box-sizing: border-box;
    background: #f7f7f7;
    padding: 20px;
    font-family: 'Arial', sans-serif;
}

.header {
    margin-bottom: 30px;
}

.upload-demo, .download-demo {
    display: flex;
    align-items: center;
    gap: 15px;
    margin-bottom: 20px;
    padding: 20px;
    background: white;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

input[type="file"] {
    padding: 8px;
    font-size: 16px;
}

.text-input {
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    flex-grow: 1;
}

button {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.upload-button {
    background-color: #007BFF;
    color: white;
}

.upload-button:hover {
    background-color: #0056b3;
}

.download-button {
    background-color: #28a745;
    color: white;
}

.download-button:hover {
    background-color: #218838;
}
</style>
