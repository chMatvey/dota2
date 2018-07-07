export default function item(state = [], action) {
    switch(action.type){
        case 'ADD_ITEM':{
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
