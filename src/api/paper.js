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