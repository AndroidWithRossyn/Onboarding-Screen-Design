/**
 * Copyright (c) 2024. Rossyn
 * <p>
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rossyn.onboarding.design
/**
 * Data model representing a single onboarding screen.
 * @property title The heading text of the screen
 * @property description The detailed description text
 * @property screenImg Resource ID of the screen's illustration
 */
data class ScreenItem(val title: String, val description: String, val screenImg: Int)

