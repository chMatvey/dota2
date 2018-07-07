export default function hero(state = [], action) {
    switch(action.type){
        case 'ADD_HERO':{
            return[
                ...state,
                action.payload
            ]
        }
        case 'DELETE_HERO':{
            state = [];
            break;
        }
        default: break;
    }
    return state
}
