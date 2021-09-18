import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {fetchRequestTypes, saveForm} from "../http/requestTypeAPI";
import {Context} from "../index";
import {CONTACT_FORM_ROUTE} from "../utils/const";
import {useHistory} from "react-router-dom";

const Form = observer(() => {
    const history = useHistory()
    const {requestType} = useContext(Context)
    const [policyNumber,setPolicyNumber] = useState('')
    const [surname,setSurname] = useState('')
    const [name,setName] = useState('')
    const [requestMessage,setRequestMessage] = useState('')
    const [requestTypeId, setRequestTypeId] = useState(null)

    useEffect(() => {
        fetchRequestTypes().then(data => {
            requestType.setTypes(data)
            setRequestTypeId(data[0].id)
            console.log(data)
        })
    }, [])

    const sendRequest = async () => {
        fetch(process.env.REACT_APP_API_URL + "api/v1/contact-forms", {
            method: 'POST',
            headers: {
                "Accept": "application/json",
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                policyNumber: policyNumber,
                requestTypeId: requestTypeId,
                name: name,
                surname:  surname,
                requestMessage: requestMessage
            })
        })
            .then(response => {
                return response.json();
            })
            .then(responseData => {
                console.log(responseData);
                window.location.href = "/"
                return responseData;})
            .catch(err => {
                alert("fetch error" + err);
            });
    }

    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="card-body">
                            <h3 className="mb-3 header-title">Contact us</h3>

                            <form>
                                <div className="mb-3">
                                    <label htmlFor="salesmanInput" className="form-label">Kind of Request</label>
                                    <select value={requestTypeId} onChange={e => setRequestTypeId(e.target.value)} id="salesmanInput" className="form-select">
                                        {requestType.types.map(type =>
                                            <option value={type.id}>{type.requestTypeName}</option>
                                        )}
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Policy namber</label>
                                    <input value={policyNumber} onChange={e => setPolicyNumber(e.target.value)} type="text" className="form-control" id="exampleInputPassword1"
                                           placeholder="Policy namber" />
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Name</label>
                                    <input value={name} onChange={e => setName(e.target.value)}  type="text" className="form-control" id="exampleInputPassword1"
                                           placeholder="Name" />
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Surname</label>
                                    <input value={surname} onChange={e => setSurname(e.target.value)} type="text" className="form-control" id="exampleInputPassword1"
                                           placeholder="Surname" />
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Your request</label>
                                    <br/>
                                    <textarea value={requestMessage} onChange={e => setRequestMessage(e.target.value)} type="text" className="textarea-message"
                                           placeholder="Request message" />
                                </div>

                                <button onClick={sendRequest} type="button" className="btn btn-primary waves-effect waves-light">SEND REQUEST</button>
                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
});

export default Form;