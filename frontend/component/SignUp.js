import React, {Component} from 'react';
import Head from './part/Head'

class SignUp extends Component {
    render() {
        return (
            <div>
                <Head/>
                <div className="jumbotron login-content padding-top">
                    <form method="post" action="http://localhost:8000/login">
                        <fieldset>
                            <legend align="center">Регистрация</legend>
                            <div className="form-group">
                                <label>Логин</label>
                                <input type="text" name="username" className="form-control" placeholder="Введите ваш логин"/>
                                <small className="form-text text-muted">Логин должен содерржать не менее 3 символов</small>
                            </div>
                            <div className="form-group">
                                <label>Пароль</label>
                                <input type="password" name="password" className="form-control" placeholder="Введите ваш пароль"/>
                                <small className="form-text text-muted">Пароль должен содерржать не менее 4 символов</small>
                            </div>
                            <div className="form-group">
                                <label>Повторите пароль</label>
                                <input type="password" name="password" className="form-control" placeholder="Введите ваш пароль"/>
                                <small className="form-text text-muted">Введенные пароли должны совпадать</small>
                            </div>
                            <button type="submit" className="btn btn-primary">Оправить</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        )
    }
}

export default SignUp;
