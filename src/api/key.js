import request from '@/utils/request.js'

// 
export const keywordListService = (name) => {
    return request.get('/paper_key/id?name='+name);
}

// 
export const paperListService = (keywordData) => {
    return request.get('/paper_key/keyword?keyword='+ keywordData);
}

// 关键词
export const addService = (pid,kid) => {
    return request.post(`/paper_key/add?name1=${pid}&name2=${kid}`);
}

// 关键词修改
export const deleService = (pid,kid) => {
    return request.delete(`/paper_key/delete?name1=${pid}&name2=${kid}`);
}
