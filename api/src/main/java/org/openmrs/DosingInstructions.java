/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs;

import java.util.Locale;

import org.springframework.validation.Errors;

/**
 * The instructions for different drug prescriptions can vary greatly. Implementations of this class
 * represent different ways to code those instructions. For example the simple dosing implementation
 * covers a common case like "X dose, with Y frequency, for Z duration" whereas a different
 * implementation would cover
 * "200mg for the first week, then up to 400mg for the rest of the prescription". Implementations of
 * this class should store their data in the appropriate fields on the DrugOrder and Order object.
 * In some cases they could store JSON in the dosing instructions field. <br/>
 * NOTE: Any class that implements this interface should have a default constructor.
 * 
 * @since 1.10
 */
public interface DosingInstructions {
	
	/**
	 * Get human-readable version of dosing instructions for a particular locale All dosing
	 * instructions can be localized, so the result, especially any free text may remain in the
	 * original language. In general, it's expect that most implementations will write orders in a
	 * single language and then want to translate instructions to the patient's preferred language
	 * when printing orders for the patient. In all other cases, it will want to call this method
	 * with the user's locale (i.e., <tt>context.getLocale()</tt>).
	 * 
	 * @return localized drug instructions string
	 */
	public String getDosingInstructionsAsString(Locale locale);
	
	/**
	 * Serialize dosing instructions into order
	 * 
	 * @param order DrugOrder to set dosing instructions
	 */
	public void setDosingInstructions(DrugOrder order);
	
	/**
	 * Get dosing instructions from order
	 * 
	 * @param order DrugOrder to get dosing instructions
	 * @return DosingInstructions created from DrugOrder
	 * @throws Exception if dosing type of passing order is not matched with dosing type of
	 *             implementing dosing instruction
	 */
	public DosingInstructions getDosingInstructions(DrugOrder order);
	
	public void validate(DrugOrder order, Errors errors);
}
