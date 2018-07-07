export default function section(state = [], action) {
    switch(action.type){
        case 'ADD_SECTION':{
            return[
                ...state,
                action.payload
            ]
        }
        default: break;
    }
    return state
}
