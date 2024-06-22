import request from '@/utils/request.js'

// 获得所有关键词
export const keywordListService = () => {
    return request.get('/keyword');
}

// 关键词添加
export const keywordAddService = (keywordData) => {
    return request.post('/keyword', keywordData);
}

// 关键词删除
export const keywordDeleteService = (id) => {
    return request.delete('/keyword?id=' + id);
}

// 关键词修改
export const keywordUpdateService = (keywordData) => {
    return request.put('/keyword', keywordData);
}
