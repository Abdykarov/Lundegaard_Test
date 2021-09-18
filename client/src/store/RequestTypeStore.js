import {makeAutoObservable} from "mobx";

export default class RequestTypeStore {

    constructor() {
        this._types = []
        makeAutoObservable(this)
    }

    get types() {
        return this._types;
    }

    setTypes(types) {
        this._types = types;
    }

}