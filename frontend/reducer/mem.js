const initialState = [
    "https://dota2.ru/img/memes/2018/07/53096.jpg?0"
];

export default function mem(state = [], action) {
    switch(action.type){
        case 'ADD_MEM':{
            return[
                ...state,
                action.payload
            ]
        }
        default: break;
    }
    return state
}
