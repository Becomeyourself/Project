import request from '@/utils/request.js'

// 获得所有作者
export const authorListService = () => {
    return request.get('/author');
}

// 作者添加
export const authorAddService = (authorData) => {
    return request.post('/author', authorData);
}

// 作者删除
export const authorDeleteService = (id) => {
    return request.delete('/author?id=' + id);
}

// 作者修改
export const authorUpdateService = (authorData) => {
    return request.put('/author', authorData);
}
//论文关系网络

export const authorPaperService =(author)=>{
    return request.get('/Paper/search?id='+author.id)
}

export const pidService = (name) => {
    return request.get('/author/pid?pid=' + name)
}