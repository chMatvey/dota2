import React, {Component} from 'react';
import Head from './part/Head'
import {connect} from 'react-redux';
import $ from 'jquery';
import './css/mem.css'

const urlData = "https://dota2.ru/";

class Mem extends Component {
    constructor(props){
        super(props);
        this.state = {
            url: "http://localhost:8000/get/memes",
            countLike: 1,
            isAdmin: false,
            liked: false

        };
        this.addMem = this.addMem.bind(this);
        this.getMemes = this.getMemes.bind(this);
    }

    componentDidMount(){

    }

    componentWillMount(){
        this.state.isAdmin = this.props.user.isAdmin;
    }

    addMem(id){
        $.ajax({
            url: (this.state.url),
            dataType: 'json',
            cache: false,
            success: function (data) {
                data.forEach((mem) => {
                    this.props.onAddMem(mem);
                });
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url, status, err.toString());
            }.bind(this)
        });
    }

    getMemes(){
        $.ajax({
            url: (this.state.url),
            dataType: 'json',
            cache: false,
            success: function (data) {
                data.forEach((mem) => {
                    this.props.onAddMem(mem);
                });
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url, status, err.toString());
            }.bind(this)
        });
    }

    likeClick(){
        this.setState({
            liked: !this.state.liked
        });
    }

    render() {
        const text = this.state.liked ? 'liked' : 'haven\'t liked';
        const label = this.state.liked ? 'Unlike' : 'Like';

        return (
            <div className="mem-page">
                <Head/>
                <div className="mem-content jumbotron">
                    {this.props.memes.map((mem) =>
                        <div className="card border-primary mb-3">
                            <div className="card-header">Admin</div>
                            <img className="mem-img" src="https://dota2.ru/img/memes/2018/07/53153.jpg?0"/>
                            <div className="mem-like-content">
                                <div className="mem-like-left">
                                    <button onClick={this.likeClick.bind(this)} className="btn btn-primary mem-button-like">{label}</button>
                                    <small className="form-text text-muted">
                                        you {text} this. Click to toggle.
                                    </small>
                                </div>
                                <div className="mem-like-right">
                                    <img className="like-img mem-padding-right" src="https://avatanplus.com/files/resources/original/56b6961261ed4152b93a37e1.png"/>
                                    <h1>{this.state.countLike}</h1>
                                </div>
                            </div>
                        </div>
                    )}
                    <div hidden={!this.state.isAdmin}>
                        <form className="add-mem">
                            <div className="form-group">
                                <label>Добавьте новый мем</label>
                                <input type="file" className="form-control-file" id="exampleInputFile" aria-describedby="fileHelp"/>
                                <small id="fileHelp" className="form-text text-muted">Укажите URL картинки и нажмите кнопку отправить</small>
                                <button className="btn btn-primary">Оправить</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect(
    state => ({
        memes: state.mem,
        user: state.user
    }),
    dispatch => ({
        onAddMem: (mem) => {
            dispatch({type: 'ADD_MEM', payload: mem})
        },
    })
)(Mem);
