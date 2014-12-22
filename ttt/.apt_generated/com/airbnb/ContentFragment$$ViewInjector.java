// Generated code from Butter Knife. Do not modify!
package com.airbnb;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ContentFragment$$ViewInjector {
  public static void inject(Finder finder, final com.airbnb.ContentFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492979, "field 'hint'");
    target.hint = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131492980, "field 'hintchild'");
    target.hintchild = (android.widget.TextView) view;
  }

  public static void reset(com.airbnb.ContentFragment target) {
    target.hint = null;
    target.hintchild = null;
  }
}
