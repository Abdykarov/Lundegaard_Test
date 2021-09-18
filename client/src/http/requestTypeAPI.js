import {$host} from "./index";

export const fetchRequestTypes = async () =>{
    const {data} = await $host.get('api/v1/request-types/')
    return data
}

export const saveForm = async (policyNumber,requestTypeId, name, surname, requestMessage) =>{
    const {data} = await $host.post('api/v1/contact-forms', {policyNumber,requestTypeId, name, surname, requestMessage})
    return data
}

