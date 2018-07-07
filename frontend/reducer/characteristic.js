export default function characteristic(state = [], action) {
    switch(action.type){
        case 'ADD_CHARACTERISTIC':{
            state = [];
            return[
                ...state,
                action.payload
            ]
        }
        default: break;
    }
    return state
}
