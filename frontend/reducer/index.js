import {combineReducers} from  'redux';
import {routerReducer} from 'react-router-redux';
import heroes from './hero';
import sections from './section';
import characteristic from './characteristic';
import item from './item';
import mem from './mem';
import user from './user'
import article from './article';

export default combineReducers({
    routing: routerReducer,
    heroes,
    sections,
    characteristic,
    item,
    mem,
    article,
})
