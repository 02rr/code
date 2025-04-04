import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getAgreementOne = (params) => {
    return getRequest('/agreement/getOne', params)
}
export const getAgreementList = (params) => {
    return getRequest('/agreement/getByPage', params)
}
export const getAgreementCount = (params) => {
    return getRequest('/agreement/count', params)
}
export const addAgreement = (params) => {
    return postRequest('/agreement/insert', params)
}
export const editAgreement = (params) => {
    return postRequest('/agreement/update', params)
}
export const addOrEditAgreement = (params) => {
    return postRequest('/agreement/insertOrUpdate', params)
}
export const deleteAgreement = (params) => {
    return postRequest('/agreement/delByIds', params)
}
export const auditAgreement = (params) => {
    return postRequest('/agreement/audit', params)
}
export const notAuditAgreement = (params) => {
    return postRequest('/agreement/notAudit', params)
}
export const signAgreement = (params) => {
    return postRequest('/agreement/sign', params)
}