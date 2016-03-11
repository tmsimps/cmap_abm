#!/usr/bin/env python
'''
    results.py
    Author: npeterson
    Revised: 3/11/16
    ---------------------------------------------------------------------------
    A module for reading TMM output files and matrix data into an SQL database
    for querying and summarization.

'''
from abm import ABM
from comparison import Comparison


### SCRIPT MODE ###
def main(
        base_dir=r'X:\new_server_tests\test_5pct',
        test_dir=r'X:\new_server_tests\test_50pct_newsocec_3sp',
        base_pct=0.05,
        test_pct=0.5,
        base_build=False,
        test_build=False
    ):
    print '\n{0:*^50}'.format(' P R O C E S S I N G ')
    print '\n{0:=^50}\n'.format(' BASE NETWORK ')
    base = ABM(base_dir, base_pct, base_build)
    base.open_db()
    base.print_mode_share()
    base.print_transit_stats()
    base.print_ptrips_by_class()
    base.print_vmt_by_speed()
    base.close_db()
    print base
    print ' '

    print '\n{0:=^50}\n'.format(' TEST NETWORK ')
    test = ABM(test_dir, test_pct, test_build)
    test.open_db()
    test.print_mode_share()
    test.print_transit_stats()
    test.print_ptrips_by_class()
    test.print_vmt_by_speed()
    test.close_db()
    print test
    print ' '

    print '\n{0:=^50}\n'.format(' COMPARISON ')
    comp = Comparison(base, test)
    print comp
    print ' '

    print '\n{0:*^50}'.format(' R E S U L T S ')
    comp.open_dbs()

    comp.print_mode_share_change()
    comp.print_transit_stats_change()
    comp.print_ptrips_by_class_change()
    comp.print_vmt_by_speed_change()
    #comp.export_persontrips_csv(os.path.join(base._TEST_DIR, 'persontrips_by_zn_o.csv'), 'zone', 'origin')
    #comp.export_persontrips_csv(os.path.join(base._TEST_DIR, 'persontrips_by_zn_d.csv'), 'zone', 'destination')
    #comp.export_persontrips_csv(os.path.join(base._TEST_DIR, 'persontrips_by_sz_o.csv'), 'subzone', 'origin')
    #comp.export_persontrips_csv(os.path.join(base._TEST_DIR, 'persontrips_by_sz_d.csv'), 'subzone', 'destination')
    comp.print_new_all()
    comp.print_new_auto()
    comp.print_new_dtt()
    comp.print_new_wtt()
    comp.print_new_other()
    comp.print_auto_trips_affected()
    #comp.print_auto_ptrips_affected()

    comp.close_dbs()

    return (base, test, comp)

if __name__ == '__main__':
    main()
