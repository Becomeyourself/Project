import request from '@/utils/request.js'
//获得所有期刊
export const journalListService = ()=>{

    return request.get('/journal')
}

//期刊添加
export const journalAddService = (journalData)=>{
    return request.post('/journal',journalData);
}

//期刊删除
export const journalDeleteService = (id)=>{
    return request.delete('/journal?id='+id)
}
//期刊修改

export const journalUpdateService = (journalData) =>{
    return request.put('/journal',journalData)
}