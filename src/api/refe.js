import request from '@/utils/request.js'

// 我参考了谁
export const sealistService = (name) => {
    return request.get('/Paper/searchrefe?name='+name);
}

//谁参考了我
export const searefeService = (name) => {
    return request.get('/Paper/searchpa?name=' + name);
}


export const addService = (name, reference) => {
    return request.post(`/Paper/addrefe?name1=${name}&name2=${reference}`);
}


export const deleService = (name, reference) => {
    return request.post(`/Paper/delerefe?name1=${name}&name2=${reference}`);
}
