import React, {Component} from 'react';
import Head from './part/Head';
import {connect} from 'react-redux';
import $ from 'jquery';
import './css/characteristic.css';

const urlData = "https://dota2.ru/";

class Characteristic extends Component{
    constructor(props){
        super(props);
        this.state = {
            url: "http://localhost:8000/get/characteristic?hero_id=",
            id: 0,
            isAuth: false,
            isAdmin: false,
        };
        this.addCharacteristic = this.addCharacteristic.bind(this);
        this.deleteComment = this.deleteComment.bind(this);
    }

    componentDidMount(){
        this.addCharacteristic(this.props.params.id);
    }

    addCharacteristic(id){
        $.ajax({
            url: (this.state.url + id),
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({
                    id: data.id
                });
                this.props.onAddCharacteristic(data);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url + id, status, err.toString());
            }.bind(this)
        });
    }

    sendComment(){
        $.ajax({
            url: ("http://localhost:8000/add/comment?charact_id=" + this.state.id + "&content=" + this.comment.value),
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.props.onAddCharacteristic(data);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error("http://localhost:8000/add/comment?charact_id=" + this.state.id + "&content=" + this.comment.value, status, err.toString());
            }.bind(this)
        });
        this.comment.value = "";
    }

    headUpdate(data){
        this.setState({
            isAdmin: data.isAdmin,
            isAuth: data.isAuth
        })
    }

    deleteComment(id){
        $.ajax({
            url: ("http://localhost:8000/delete/comment?comment_id=" + id),
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.addCharacteristic(this.state.id);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url, status, err.toString());
            }.bind(this)
        });
    }

    render(){
        return(
            <div>
                <Head dataUpdate={this.headUpdate.bind(this)}/>
                {this.props.characteristics.map((charact) =>
                    <div className="jumbotron content-table" key={charact.id}>
                        <div className="navbar navbar-expand-lg navbar-dark bg-primary">
                            <h2 className="m-0 m-auto">{charact.hero.name}</h2>
                        </div>
                        <div className="display-flex m-lg-2">
                            <div className="flex-direction-column hero-characteristics">
                                <div className="display-flex">
                                    <div className="hero-info-left">
                                        <div className="hero-info-img"><img src={urlData + charact.img}/></div>
                                        <table className="table table-hover table-hero-info-left">
                                            <thead>
                                            <tr>
                                                <th scope="col">Уровень</th>
                                                <th colSpan={2} scope="colgroup">Навык</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                {charact.ability1.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                {charact.ability2.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                {charact.ability3.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                {charact.ability4.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div>
                                            <h5>Видео-обзор</h5>
                                            <iframe src={charact.video}/>
                                        </div>
                                    </div>
                                    <div className="hero-info-middle">
                                        <table className="table table-hover table-hero-info-middle">
                                            <thead><tr><td scope="row" colSpan={4}>Характеристики</td></tr></thead>
                                            <tbody>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_str.png"/></td>
                                                <td colSpan={3}>{charact.power}</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_agi.png"/></td>
                                                <td colSpan={3}>{charact.agility}</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_int.png"/></td>
                                                <td colSpan={3}>{charact.intelligence}</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_attack.png"/></td>
                                                <td colSpan={3}>{charact.damage}</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_defense.png"/></td>
                                                <td colSpan={3}>{charact.defence}</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://dota2.ru/img/icons/overviewicon_speed.png"/></td>
                                                <td colSpan={3}>{charact.speed}</td>
                                            </tr>
                                            <tr>
                                                <td>Уровень</td>
                                                {charact.stat.level.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Урон</td>
                                                {charact.stat.damage.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Здоровье</td>
                                                {charact.stat.health.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Мана</td>
                                                {charact.stat.mana.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Защита</td>
                                                {charact.stat.defence.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Время атаки</td>
                                                {charact.stat.timeAttack.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Атак в секунду</td>
                                                {charact.stat.attackInSecond.split('&').map((str) =>
                                                    <td>{str}</td>
                                                )}
                                            </tr>
                                            <tr>
                                                <td>Дальность обзора</td>
                                                <td colSpan={3}>{charact.stat.vision}</td>
                                            </tr>
                                            <tr>
                                                <td>Дальность атаки</td>
                                                <td colSpan={3}>{charact.stat.distanceAttack}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div className="hero-history">
                                    <h3 align="center">История</h3>
                                    <p>{charact.bio}</p>
                                </div>
                                <h4 align="center">Комментарии</h4>
                                {charact.comments.map((comment) =>
                                <div key={comment.id}>
                                    <div className="card text-white bg-primary mb-3">
                                        <div className="card-header comment-head">
                                            <div>{comment.user.username}</div>
                                            <button className="btn btn-primary btn-sm" hidden={!this.state.isAdmin}
                                                    onClick={() =>{
                                                        this.deleteComment(comment.id)
                                                    }}>delete</button>
                                        </div>
                                        <div className="card-body">
                                            <p className="card-text">{comment.content}</p>
                                        </div>
                                    </div>
                                </div>)}
                                <div className="charact-comment" hidden={!this.state.isAuth}>
                                    <textarea className="input-group-text"  placeholder="Поделитесь вашими мыслями..."
                                    ref={(textarea) => {
                                        this.comment = textarea;
                                    }}/>
                                    <button className="btn btn-primary" onClick={this.sendComment.bind(this)}>Отправить</button>
                                </div>
                            </div>
                            <div className="hero-info-right flex-direction-column">
                                {charact.skill.map((skill) =>
                                <table className="table table-hover table-hero-skill">
                                    <thead><tr><td colSpan={2}>{skill.name}</td></tr></thead>
                                    <tbody><tr>
                                        <td><img src={urlData + skill.img}/></td>
                                        <td>{skill.description}</td>
                                    </tr></tbody>
                                </table>)}
                            </div>
                        </div>
                    </div>
                )}
            </div>
        )
    }
}

export default connect(
    state => ({
        characteristics: state.characteristic
    }),
    dispatch => ({
        onAddCharacteristic: (characteristic) => {
            dispatch({type: 'ADD_CHARACTERISTIC', payload: characteristic})
        }
    })
)(Characteristic);
