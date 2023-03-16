// This file is auto-generated. Changes will be overwritten when updating localizations!
@file:Suppress("RedundantVisibilityModifier","DEPRECATION","unused","SpellCheckingInspection")

package com.seek.assignment.core.model

import kotlin.Deprecated
import kotlin.String

@Deprecated(message = "Usage outside of StringKey is not supported and highly discouraged")
public abstract class StringKeyInterface {
  public inner class Key(
    public val key: String,
  )
}

public object StringKey : StringKeyInterface() {
    public val jobPostings: StringKeyItem = StringKeyItem(Key("Job_Postings"))
    public val homeHome: StringKeyItem = StringKeyItem(Key("Home_Home"))
    public val okButton: StringKeyItem = StringKeyItem(Key("Ok_Button"))
}
