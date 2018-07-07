export default function article(state = [], action) {
    switch(action.type){
        case 'ADD_ARTICLE':{
            return[
                ...state,
                action.payload
            ]
        }
        case 'DELETE_ARTICLES':{
            state = [];
            break;
        }
        default: break;
    }
    return state
}
