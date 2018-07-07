const initialState = [{"id":2,"username":"admin","authorities":["ADMIN"]}];

export default function user(state = [], action) {
    switch(action.type){
        case 'ADD_USER':{
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
