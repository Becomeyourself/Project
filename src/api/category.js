import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js'
//获得所有分类
export const categoryListService = ()=>{

    return request.get('/category')
}

//分类添加
export const categoryAddService = (categoryData)=>{
    return request.post('/category',categoryData);
}

//分类删除
export const categoryDeleteService = (id)=>{
    return request.delete('/category?id='+id)
}
//分类修改

export const categoryUpdateService = (categoryData) =>{
    return request.put('/category',categoryData)
}
//父类别查询子类别
export const cateseaService = (name) => {
    return request.get('/category/season?name=' + name)
}

export const caterootService = () => {
    return request.get('/category/root')
}