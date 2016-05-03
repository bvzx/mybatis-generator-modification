/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator;

import org.mybatis.generator.api.ShellRunner;

/**
 * 杭州动享互联网技术有限公司
 *
 * @ClassName: org.mybatis.generator.MBGTest
 * @Author bvzx
 * @Date 2016年04月13日
 * @Description: TODO 这个类是做什么的
 */
public class MBGTest {


    public static void main(String[] args) {
        ShellRunner.start(new String[]{
                "-configfile", ".\\mybatis-generator-core\\src\\test\\resources\\generatorConfig.xml","-overwrite"
        });
    }
}
