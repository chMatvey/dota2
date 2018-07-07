import React, {Component} from 'react';
import Head from './part/Head'
import {connect} from 'react-redux';
import $ from 'jquery';
import './css/item.css'

const urlData = "https://dota2.ru/";

class Item extends Component {
    constructor(){
        super();
        this.state = {
            url: "http://localhost:8000/get/item/?id=",
        };
        this.addItem = this.addItem.bind(this);
    }

    componentDidMount(){
        this.addItem(this.props.params.id);
    }

    addItem(id){
        $.ajax({
            url: (this.state.url + id),
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.props.onAddItem(data);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url + id, status, err.toString());
            }.bind(this)
        });
    }

    render() {
        return (
            <div className="item-page">
                <Head/>
                <div className={"item-padding"}>
                    {this.props.item.map((item) =>
                        <div className="jumbotron item-content">
                            <div className="item-display-flex">
                                <img src={urlData + item.img}/>
                                    <div className="item-name-and-price">
                                    <h4>{item.name}</h4>
                                    <div className="item-display-flex">
                                        <img className="gold-img" src="https://dota2.ru/img/icons/gold.png"/>
                                        <h4>{item.price}</h4>
                                    </div>
                                </div>
                            </div>
                            <p>{item.description}</p>
                            <em className="item-em">{item.history}</em>
                        </div>
                    )}
                </div>
            </div>
        )
    }
}

export default connect(
    state => ({
        item: state.item,
    }),
    dispatch => ({
        onAddItem: (item) => {
            dispatch({type: 'ADD_ITEM', payload: item})
        },
    })
)(Item);
