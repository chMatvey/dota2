import React, {Component} from 'react';
import Head from './part/Head'
import './css/sign.css'

class SignIn extends Component {
    constructor(props){
        super(props);
    }

    btnClick(){
        this.refs.child.isAuthenticated();
    }

    render() {
        return (
            <div>
                <Head ref="head"/>
                <div className="jumbotron login-content padding-top">
                    <form>
                        <fieldset>
                            <legend align="center">Авторизация</legend>
                            <div className="form-group">
                                <label>Логин</label>
                                <input type="text" name="username" className="form-control" placeholder="Введите ваш логин"/>
                            </div>
                            <div className="form-group">
                                <label>Пароль</label>
                                <input type="password" name="password" className="form-control" placeholder="Введите ваш пароль"/>
                            </div>
                            <button type="submit" onClick={this.btnClick.bind(this)} className="btn btn-primary">Оправить</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        )
    }
}

export default SignIn;
