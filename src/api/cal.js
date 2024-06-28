import request from '@/utils/request.js'

// 获得所有关键词
export const calService = (name) => {
    return request.get('/Paper/cal?name='+name);
}

