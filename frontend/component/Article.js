import React, {Component} from 'react';
import Head from './part/Head'
import {connect} from 'react-redux';
import './css/hero.css';
import {Link} from 'react-router';
import $ from 'jquery';
import ScrollEvent from 'react-onscroll';
import "./css/article.css"

class Article extends Component {
    constructor() {
        super();
        this.state = {
            limit: 10,
            offset: 0,
            isAdmin: false,
            url: "http://localhost:8000/get/articles?limit=",
        };
        this.deleteArticle = this.deleteArticle.bind(this);
    }

    componentDidMount(){
        if (this.props.articles.length < 10){
            this.props.onDeleteArticle();
            this.getArticle();
        }
    }

    getArticle(){
        $.ajax({
            url: (this.state.url + this.state.limit + "&offset=" + this.state.offset),
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.log(data);
                data.forEach((article) => {
                    this.props.onAddArticles(article);
                });
                this.state.offset+=10;
                if(this.props.articles.length < 10){
                    this.setState({
                        offset: this.props.articles.length,
                    })
                }
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url + this.state.limit + "&offset=" + this.state.offset, status, err.toString());
            }.bind(this)
        });
    }

    sendArticle() {
        $.ajax({
            url: ("http://localhost:8000/save/article?title=" + this.article.value + "&content=" + this.article.value),
            dataType: 'json',
            cache: false,
            success: function (data) {
                alert(data.value);
                this.getArticle();
            }.bind(this),
            error: function (xhr, status, err) {
                console.error("http://localhost:8000/save/article?title=" + this.article.value + "&content" + this.article.value, status, err.toString());
            }.bind(this)
        });
    }

    handleScrollCallback(){
        const scroll_height = $(document).height();
        const scroll_position = $(window).height() + $(window).scrollTop();
        if (scroll_position === scroll_height) {
            this.getArticle();
        }
    }

    headUpdate(data){
        console.log(data.isAdmin);
        this.setState({
            isAdmin: data.isAdmin
        });
    }

    deleteArticle(id){
        $.ajax({
            url: ("http://localhost:8000/delete/article?article_id=" + id),
            dataType: 'json',
            cache: false,
            success: function (data) {
                alert(data.value);
                this.setState({
                    offset: 0
                });
                this.props.onDeleteArticle();
            }.bind(this),
            error: function (xhr, status, err) {
                console.error("http://localhost:8000/delete/article" + id, status, err.toString());
            }.bind(this)
        });
    }

    render() {
        return (
            <div>
                <Head dataUpdate={this.headUpdate.bind(this)}/>
                <ScrollEvent handleScrollCallback={this.handleScrollCallback.bind(this)}/>
                <div className="jumbotron content-table">
                    <div className="charact-comment article-comment" hidden={!this.state.isAdmin}>
                        <legend align="center">Создайте новую статью</legend>
                        <input className="input-group-text article-title" placeholder="Заголовок статьи"/>
                        <textarea className="input-group-text" placeholder="Содержимое статьи"
                                  ref={(textarea) => {this.article = textarea;}}/>
                        <button className="btn btn-primary" onClick={this.sendArticle.bind(this)}>Отправить</button>
                    </div>
                    {this.props.articles.map((article) =>
                        <div className="card text-white bg-primary mb-3">
                            <div className="card-header comment-head">
                                <small className="form-text text-muted">Название</small>
                                <div align="center">{article.title}</div>
                                <small hidden={this.state.isAdmin} className="form-text text-muted">Dota2</small>
                                <button hidden={!this.state.isAdmin} onClick={() => {this.deleteArticle(article.id)}} className="btn btn-primary btn-sm">delete</button>
                            </div>
                            <div className="card-body">
                                <p>{article.content}</p>
                            </div>
                            <div className="card-header comment-head">
                                <small className="form-text text-muted">Автор</small>
                                {article.user.username}
                            </div>
                        </div>
                    )}
                </div>
            </div>
        )
    }
}

export default connect(
    state => ({
        articles: state.article,
    }),
    dispatch => ({
        onAddArticles: (article) => {
            dispatch({type: 'ADD_ARTICLE', payload: article})
        },
        onDeleteArticle: () => {
            dispatch({type: 'DELETE_ARTICLES'});
        }
    })
)(Article);
