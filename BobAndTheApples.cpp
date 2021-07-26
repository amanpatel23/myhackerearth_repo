/*
Created By: Aman Patel
Date: 26-07-2021
*/

#include <iostream>
#include <vector>
#include <map>
#include <list>
#include <set>
#include <algorithm>
#include <queue>
#include <stack>
#include <cstring>
#include <cmath>
#include <bitset>
#include <string>
#include <unordered_set>
#include <unordered_map>
#include <cstdlib>
#include <iomanip>

#define int long long
#define mod 1000000007
#define i_max INT_MAX
#define i_min INT_MIN
#define s_i set<int>
#define v_i vector<int>
#define v_s vector<string>
#define v_c vector<char>
#define stk_i stack<int>
#define q_i queue<int>
#define qp_ii queue<pair<int, int>>
#define pqp_ii priority_queue<pair<int, int>>
#define vp_ii vector<pair<int, int>>
#define um_ii unordered_map<int, int>
#define m_ii map<int, int>
#define p_ii pair<int, int>
#define all(a) (a).begin(), (a).end()
#define mem1(a) memset(a, -1, sizeof(a))
#define mem0(a) memset(a, 0, sizeof(a))
#define lbnd lower_bond
#define ubnd upper_bond
#define ff first
#define ss second
#define mp make_pair
#define pb push_back
#define nline "\n"
#define yes (cout << "YES" << nline)
#define no (cout << "NO" << nline)
#define rep(i, a, b) for(int i = a; i < b; i++)
#define fast ios_base::sync_with_stdio(false), cin.tie(nullptr), cout.tie(nullptr)

using namespace std;

vp_ii vitaminAndPrice;
vector<vector<vector<int>>> dp;
int n, m;
int _count = 0;

int maxVitamines(int i, int totalPrice, int flag) {
    _count++;
    if (i < 0)
        return 0;

    if (dp[i][totalPrice][flag] != -1)
        return dp[i][totalPrice][flag];

    if (flag) {
        if (vitaminAndPrice[i].ss / 2 + totalPrice > m)
            dp[i][totalPrice][flag] = maxVitamines(i - 1, totalPrice, flag);
        else if (vitaminAndPrice[i].ss + totalPrice > m)
            dp[i][totalPrice][flag] = max(maxVitamines(i - 1, totalPrice, flag),
                                          vitaminAndPrice[i].ff +
                                          maxVitamines(i - 1, totalPrice + (vitaminAndPrice[i].ss / 2), 0));
        else {
            dp[i][totalPrice][flag] = max({maxVitamines(i - 1, totalPrice, flag),
                                           vitaminAndPrice[i].ff +
                                           maxVitamines(i - 1, totalPrice + vitaminAndPrice[i].ss, flag),
                                           vitaminAndPrice[i].ff +
                                           maxVitamines(i - 1, totalPrice + (vitaminAndPrice[i].ss / 2), 0)});
        }

        return dp[i][totalPrice][flag];
    }

    if (vitaminAndPrice[i].ss + totalPrice > m)
        dp[i][totalPrice][flag] = maxVitamines(i - 1, totalPrice, flag);
    else
        dp[i][totalPrice][flag] = max(maxVitamines(i - 1, totalPrice, flag),
                                      vitaminAndPrice[i].ff +
                                      maxVitamines(i - 1, totalPrice + vitaminAndPrice[i].ss, flag));

    return dp[i][totalPrice][flag];
}

void solve() {

    cin >> n >> m;
    vitaminAndPrice = vp_ii(n);

    rep (i, 0, n) {
        cin >> vitaminAndPrice[i].ff >> vitaminAndPrice[i].ss;
    }

    dp = vector<vector<vector<int>>>(n + 5, vector<vector<int>>(m + 5, vector<int>(2, -1)));
    int result = maxVitamines(n - 1, 0, 1);

    cout << result << nline;
    //cout << _count << nline;
}

int32_t main() {

    fast;

#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    int t;
    t = 1;
    cin >> t;

    while (t--) {
        solve();
    }

    return 0;
}
