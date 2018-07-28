import React, {Component} from 'react';
import {Link} from 'react-router';
import './css/head.css'
import $ from "jquery";

class Head extends Component{
    constructor(props){
        super(props);
        this.state = {
            dataUpdate: props.dataUpdate,
            url: "/get/user",
            isAuth: false,
            isAdmin: false,
            login: "",
        };
        this.isAuthenticated = this.isAuthenticated.bind(this);
    }

    componentDidMount(){
        this.isAuthenticated()
    }

    isAuthenticated(){
        $.ajax({
            url: (this.state.url),
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (typeof data.error === 'undefined'){
                    let username = data.value.username;
                    let isAdmin = false;
                    if (data.value.authorities[0].localeCompare("ADMIN") === 0){
                        isAdmin = true;
                    }
                    this.setState({
                        login: username,
                        isAdmin: isAdmin,
                        isAuth: true,
                    })
                } else {
                    this.setState({
                        isAuth: false,
                        isAdmin: false
                    })
                }
                if (this.state.dataUpdate !== null && typeof this.state.dataUpdate === "function"){
                    this.state.dataUpdate(this.state);
                }
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url, status, err.toString());
            }.bind(this)
        });
    }

    render(){
        return(
            <div className="main-header">
                <ul className="dark-header">
                    <li className="first-child"><h1><Link to="/">
                        <img src="/img/logo.jpg"/>
                    </Link></h1></li>
                    <li><Link to="/heroes">Heroes</Link></li>
                    <li><Link to="/items">Items</Link></li>
                    <li><Link to="/articles">Articles</Link></li>
                    <li hidden={this.state.isAuth}><Link to="/signIn">Sign in</Link></li>
                    <li hidden={this.state.isAuth}><Link to="/signUp">Sign up</Link></li>
                    <li hidden={!this.state.isAuth}><a href="/logout">Logout</a></li>
                    <li hidden={!this.state.isAuth}><Link>{this.state.login}</Link></li>
                    <li className="last-child"><h1><Link to="/">
                        <img src="/img/logo.jpg"/>
                    </Link></h1></li>
                </ul>
            </div>
        )
    }
}

export default Head;
