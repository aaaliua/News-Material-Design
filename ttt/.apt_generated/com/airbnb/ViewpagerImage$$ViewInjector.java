// Generated code from Butter Knife. Do not modify!
package com.airbnb;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ViewpagerImage$$ViewInjector {
  public static void inject(Finder finder, final com.airbnb.ViewpagerImage target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492976, "field 'background'");
    target.background = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131492974, "field 'pagers'");
    target.pagers = (com.aaaliua.view.HackyViewPager) view;
    view = finder.findRequiredView(source, 2131492975, "field 'mIndicator'");
    target.mIndicator = (com.aaaliua.view.CirclePageIndicator) view;
  }

  public static void reset(com.airbnb.ViewpagerImage target) {
    target.background = null;
    target.pagers = null;
    target.mIndicator = null;
  }
}
