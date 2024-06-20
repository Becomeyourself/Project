import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js'

//论文列表查询
export const paperListService = ()=>{

    return request.get('/Paper')
}

//论文添加
export const paperAddService = (paperData)=>{
    return request.post('/Paper/add',paperData);
}

//论文删除
export const paperDeleteService = (id)=>{
    return request.delete('/Paper?id='+id)
}

//按作者姓名查询
export const authorFindService = (name)=>{
    return request.get('/Paper/Author?author_name='+name)
}
//按分类查询
export const categoryFindService = (category)=>{
    return request.get('/Paper/Categories?categories='+category)
}

//按照标题查论文
export const titleFindService = (title)=>{
    return request.get('/Paper/Title?title='+title)
}

//按照关键字查论文
export const keywordFindService = (Keyword)=>{
    return request.get('/Paper/Keyword?keyword='+Keyword)
}

//按照刊物查论文
export const journalFindService = (Journal)=>{
    return request.get('/Paper/Journal?journal='+Journal)
}