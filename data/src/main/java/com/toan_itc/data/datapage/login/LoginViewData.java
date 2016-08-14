/*
 * Copyright (C) 2016 david.wei (lighters)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*


package com.toan_itc.data.viewdata.login;

import com.lighters.github.data.net.mingdao.common.AuthEntity;
import com.lighters.github.data.repository.login.UserRepository;
import com.lighters.github.domain.viewdata.base.BaseViewData;

import javax.inject.Inject;

import rx.Observable;

*/
/**
 * Created by david on 16/3/26.
 * Email: huangdiv5@gmail.com
 * GitHub: https://github.com/david-wei
 *//*

public class LoginViewData extends BaseViewData<AuthEntity> {

    private UserRepository mUserRepository;

    @Inject
    public LoginViewData(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public Observable<AuthEntity> login(String userName, String password) {
        return mUserRepository.login(userName, password);
    }
}
*/
